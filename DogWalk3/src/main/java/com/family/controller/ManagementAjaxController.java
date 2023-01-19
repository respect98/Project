package com.family.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.pet.mapper.MainCategoryDao;
import com.family.pet.mapper.ScheduleMapper;
import com.family.pet.mapper.SubCategoryDao;
import com.family.pet.model.MainCategory;
import com.family.pet.model.ScheduleVO;
import com.family.pet.model.SubCategory;
import com.family.pet.service.PetService;
import com.member.controller.MemberController;




@RestController
@RequestMapping("/management/")
public class ManagementAjaxController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	@Autowired
	private PetService petService;
	
	@Autowired
	
	
	// 대분류 코드 가져오기
	@RequestMapping("getMainCategory.bit")
	public List<MainCategory> getMainCategory() {

		List<MainCategory> list = null;
		
		try {
			
			MainCategoryDao dao = sqlsession.getMapper(MainCategoryDao.class);
			list = dao.getMainCategory();
			logger.info("대분류코드 가져오기 완료");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return list;
	}
	
	// 소분류 코드 가져오기 - 개
	@RequestMapping("getSubCategory_dog.bit")
	public List<SubCategory> getSubCategory_dog() {
		
		List<SubCategory> list = null;
		
		try {
			
			SubCategoryDao dao = sqlsession.getMapper(SubCategoryDao.class);
			list = dao.getSubCategory_dog();
			logger.info("소분류 코드(개) 가져오기 완료");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	// 소분류 코드 가져오기 - 고양이
	@RequestMapping("getSubCategory_cat.bit")
	public List<SubCategory> getSubCategory_cat() {
		
		List<SubCategory> list = null;
		
		try {
			
			SubCategoryDao dao = sqlsession.getMapper(SubCategoryDao.class);
			list = dao.getSubCategory_cat();
			logger.info("소분류 코드(고양이) 가져오기 완료");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	// 일정 추가하기
	@RequestMapping("insertSchedule.bit")
	public int newSchedule(ScheduleVO schedule) {
		System.out.println("newSchedule...");
		int result = 0;
		
		try {
			ScheduleMapper dao = sqlsession.getMapper(ScheduleMapper.class);
			result = dao.newSchedule(schedule);
			System.out.println("newSchedule...result: "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return schedule.getSindex();
	}
	
	// 전체 일정 불러오기
	@GetMapping(value="getSchedule.bit",produces="application/json;  charset=UTF-8")
	public Map<String, Object> getSchedule(String userid) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<ScheduleVO> schedule = null;
		
		try {
			
			schedule = petService.getSchedule(userid);
			map.put("schedule", schedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("schedule================"+schedule);
		System.out.println("userid"+userid);
		return map;
	}
	
	// 일정 업데이트
	@RequestMapping("updateSchedule.bit")
	public int updateSchedule(ScheduleVO schedule) {
		
		System.out.println("올데이: "+ schedule.getAllDay());
		
		int result = 0;
		try {
			ScheduleMapper dao = sqlsession.getMapper(ScheduleMapper.class);
			result = dao.updateSchedule(schedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 드래그 앤 드롭 일정 업데이트
	@RequestMapping("dndUpdateSchedule.bit")
	public int dndUpdateSchedule(ScheduleVO schedule) {
		
		System.out.println("올데이: "+ schedule.getAllDay());
		
		int result = 0;
		try {
			ScheduleMapper dao = sqlsession.getMapper(ScheduleMapper.class);
			result = dao.dndUpdateSchedule(schedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	// 일정 삭제하기
	@RequestMapping("deleteSchedule.bit")
	public int deleteSchedule(String sindex) {
		
		int result = 0;
		
		try {
			ScheduleMapper dao = sqlsession.getMapper(ScheduleMapper.class);
			result = dao.deleteSchedule(sindex);
			
			if(result==1) {
				logger.info(sindex+"번 일정 삭제 성공");
			}else {
				logger.info(sindex+"번 일정 삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
