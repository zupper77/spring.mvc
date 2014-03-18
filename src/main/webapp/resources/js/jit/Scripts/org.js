var labelType, useGradients, nativeTextSupport, animate;
var url = "/org.json"; // 이 부분을 조직도 REST GET URL로 바꿔줍니다.
var divId = "orgchart"; // 조직도가 그려질 div 이름입니다.

(function () {
    var ua = navigator.userAgent,
        iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
        typeOfCanvas = typeof HTMLCanvasElement,
        nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
        textSupport = nativeCanvasSupport
          && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
    labelType = (!nativeCanvasSupport || (textSupport && !iStuff)) ? 'Native' : 'HTML';
    nativeTextSupport = labelType == 'Native';
    useGradients = nativeCanvasSupport;
    animate = !(iStuff || !nativeCanvasSupport);
})();

var json = [];
var selectedId = 1; // 선택된 노드(조직)
var nodesCount = 0;
var st = null; // space tree instance

// 노드 속의 글자가 클릭되면 호출되는 함수
function onNodeClicked(e, node) {
    if (e.which == 3) { // 마우스 오른쪽 클릭
        if (node._depth == 0) {
            alert("최상위 조직은 삭제가 안되요");
            return true;
        } else {
            var count = 0;
            node.eachSubnode(function (n) { count++; });

            if (count > 0) {
                alert("하위 조직이 있으면 삭제 할 수 없습니다.");
                return true;
            }
            //remove the subtree  
            st.removeSubtree(node.id, true, 'animate', {
                hideLabels: false,
                onComplete: function () {
                    //Log.write(node.name + "이(가) 삭제되었습니다.");
                }
            });
        }
    } else { // 마우스 왼쪽 클릭
        selectedId = node.id;
        st.onClick(node.id);

        //Log.write(node.name + "이(가) 선택되었습니다.");
    }
}

function init() {
    //init Spacetree
    //Create a new ST instance
    st = new $jit.ST({
        //id of viz container element
        constrained: true,
        levelsToShow: 1,
        injectInto: divId,
        orientation: "top",
        align:"center",
        //set duration for the animation
        duration: 400,
        //set animation transition type
        transition: $jit.Trans.Quart.easeInOut,
        //set distance between node and its children
        levelDistance: 50,
        //enable panning
        Navigation: {
            enable: true,
            panning: true
        },
        //set node and edge styles
        //set overridable=true for styling individual
        //nodes or edges
        Node: {
            height: 60,
            width: 90,
            type: 'rectangle',
            color: '#69a',
            overridable: true
        },

        Edge: {
            type: 'line',
            overridable: true
        },
        
        //This method is called on DOM label creation.
        //Use this method to add event handlers and styles to
        //your node.
        onCreateLabel: function (label, node) {
            label.id = node.id;
            label.innerHTML = node.name;
                //'<img width="30" height="30" src="' + node.data['imgsrc'] + '"/><br/><a>' + node.name + '</a>';
            //set label styles
            var style = label.style;
            style.width = 90 + 'px';
            style.height = 40 + 'px';
            style.cursor = 'pointer';
            style.color = '#111';
            style.fontSize = '1em';
            style.textAlign = 'center';
            style.paddingTop = '20px';

            // 노드 속의 글자가 클릭되면 호출
            $(label).mousedown(function(e) {
                onNodeClicked(e, node);
            });
        },

        // 노드(조직)을 그리기 직전에 호출되는 함수.
        // 색을 칠해 줍니다.
        onBeforePlotNode: function (node) {            
            if (node.selected) { // 선택된 노드
                node.data.$color = "#ff7";
            }
            else {
                delete node.data.$color;
            }
        },

        // 노드 사이의 선을 그리기 직전에 호출되는 함수.
        onBeforePlotLine: function (adj) {
            if (adj.nodeFrom.selected && adj.nodeTo.selected) {
                adj.data.$color = "#23A4FF";
                adj.data.$lineWidth = 3;
            }
            else {
                delete adj.data.$color;
                delete adj.data.$lineWidth;
            }
        }
    });
    //end init
}

$(document).ready(function () {
    init();

    // JSON loading
    $.getJSON(url, null, function (data) {
        $(data).each(function (index, node) {
            node.id = node.organizationCode;
            node.parentId = node.parentCode;
            //node.id = node.seq;
            //node.parentId = node.parentSeq;
            node.name = node.organizationName;
        });

        json = data;
        nodesCount = data.length;

        //load json data
        st.loadJSON(json, 0);
        //compute node positions and layout
        st.compute();
        //optional: make a translation of the tree
        st.geom.translate(new $jit.Complex(-200, 0), "current");

        //emulate a click on the root node.
        st.onClick(st.root);
    });

    // 화면 리사이즈
    $(window).resize(function () {
        st.canvas.resize($('#' + divId).width(), $('#' + divId).height());
    });

    // 노드(조직) 추가
    $('#addSubtree').click(function () {
        var subtree = { id: ++nodesCount, name: $('#txtOrg').val(), parentId: selectedId };

        var type = "animate";
        //add the subtree  
        st.addSubtree(subtree, type, {
            hideLabels: false,
            onComplete: function () {
                //Log.write("subtree " + subtree.name + " added");
            }
        });
    });

    // 선택된 노드 삭제
    $('#deleteSubtree').click(function () {
        if (confirm('선택된 노드를 삭제합니까?') == true) {
            //remove the subtree  
            st.removeSubtree(selectedId, true, 'animate', {
                hideLabels: false,
                onComplete: function () {
                    //Log.write("선택된 노드가 삭제되었습니다.");
                }
            });
        }
    });
});