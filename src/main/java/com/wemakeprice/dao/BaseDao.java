package com.wemakeprice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Repository;

/**  
 * @Description SQLID로 sql 실행하기 위한 DAO
 * @since 2013.05.09
 *  
 */
@Repository
public class BaseDao extends DaoSupport {
	
	private SqlSession sqlSession;
	private boolean externalSqlSession;
	/*
	@Override
	public void setSqlSessionFactory(org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory) {
		
	};
	*/

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        if(!this.externalSqlSession){
            this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        }
    }
	
	@Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate)
    {
        sqlSession = sqlSessionTemplate;
        this.externalSqlSession = true;
    }
	
	
	private SqlSession getSqlSession(){
		return this.sqlSession;
	}
	
	
	@Override
	protected void checkDaoConfig() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Insert Sql 실행
	 * @param sqlId
	 * @return
	 */
	private int insert(String sqlId) {
		//return this.getSqlSession().insert(sqlId);
		return this.getSqlSession().insert(sqlId);
    }
	
	/**
	 * Insert Sql 실행
	 * @param sqlId
	 * @param param
	 * @return
	 */
	public int insert(String sqlId, Object param) {
		//return getSqlSession().insert(sqlId, param);
		return this.getSqlSession().insert(sqlId, param);
    }
	
	/**
	 * Update Sql 실행
	 * @param sqlId
	 * @return
	 */
	public int update(String sqlId) {
		return getSqlSession().update(sqlId);
    }
	
	/**
	 * Update Sql 실행
	 * @param sqlId
	 * @param param
	 * @return
	 */
	public int update(String sqlId, Object param) {
		return getSqlSession().update(sqlId, param);
    }
	
	/**
	 * Delete Sql 실행
	 * @param sqlId
	 * @return
	 */
	public int delete(String sqlId) {
		return getSqlSession().delete(sqlId);
    }
	
	/**
	 * Delete Sql 실행
	 * @param sqlId
	 * @param param
	 * @return
	 */
	public int delete(String sqlId, Object param) {
		return getSqlSession().delete(sqlId, param);
    }
	
	/**
	 * 단건 조회 Sql 실행
	 * @param sqlId
	 * @return
	 */
	public Object selectOne(String sqlId) {
		return getSqlSession().selectOne(sqlId);
    }
	
	/**
	 * 단건 조회 Sql 실행
	 * @param sqlId
	 * @param param
	 * @return
	 */
	public Object selectOne(String sqlId, Object param) {
		return getSqlSession().selectOne(sqlId, param);
    }
	
	/**
	 * 다건 조회 Sql 실행
	 * @param sqlId
	 * @return
	 */
	public <E> List<E> selectList(String sqlId) {
		return getSqlSession().selectList(sqlId);
    }
	
	/**
	 * 다건 조회 Sql 실행
	 * @param sqlId
	 * @param param
	 * @return
	 */
	public <E> List<E> selectList(String sqlId, Object param) {
		return getSqlSession().selectList(sqlId, param);
    }


}
 
