package com.realnet.fnd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.realnet.fnd.model.Rn_Admin_Count;
import com.realnet.fnd.model.Rn_Dashbord;
@Repository("DashbordApproverDAO")
public class Rn_Dashbord_DaoImpl implements Rn_Dashbord_Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Rn_Dashbord> DashbordApprover_List(int user_id) {
		String sql = "SELECT KWN.CLAIM_HEADER_ID CLAIM_NUMBER, KCH.COUNTRY_CODE,KWN.WF_NOTIFICATION_MSG FROM"
				+ " RN_WF_NOTIFICATIONS_T KWN, rn_claim_header_t KCH "
				+ "WHERE KWN.CLAIM_HEADER_ID = KCH.CLAIM_HEADER_ID AND KWN.STATUS = 'Pending'	"
				+ "AND KWN.USER_ID ="+user_id+" ORDER BY KCH.LAST_UPDATE_DATE DESC";
		List<Rn_Dashbord> rn_dashbord = jdbcTemplate.query(sql, new RowMapper<Rn_Dashbord>() {

			@Override
			public Rn_Dashbord mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Dashbord rn_dashbord_t = new Rn_Dashbord();
	
				rn_dashbord_t.setClaim_number(rs.getInt("CLAIM_NUMBER"));
				rn_dashbord_t.setCountry_code(rs.getString("COUNTRY_CODE"));
				rn_dashbord_t.setWf_notification_msg(rs.getString("WF_NOTIFICATION_MSG"));
				System.out.println("country code:"+rs.getString("COUNTRY_CODE"));
				return rn_dashbord_t;
			}
			
		});
		
		return rn_dashbord;
	}
	
	
	@Override
	public List<Rn_Dashbord> DashbordApproverCount_List(int user_id) {
		String sql = "SELECT pending_claims, approved_claims, total_claims, rejected_claims, inprocess_claims FROM "
				+ "(SELECT COUNT(claim_header_id) pending_claims FROM rn_claim_header_t WHERE claim_status = 'Entered' )as pending_claims, "
				+ "(SELECT COUNT(claim_header_id) Approved_claims FROM rn_claim_header_t WHERE claim_status = 'Approved' )as Approved_claims, "
				+ "(SELECT COUNT(claim_header_id) total_claims FROM rn_claim_header_t )as total_claims, (SELECT COUNT(claim_header_id) rejected_claims "
				+ "FROM rn_claim_header_t WHERE claim_status = 'Rejected' )as rejected_claims, (SELECT COUNT(claim_header_id) inprocess_claims FROM rn_claim_header_t "
				+ "WHERE claim_status = 'In Process')as inprocess_claims";
		List<Rn_Dashbord> rn_dashbord = jdbcTemplate.query(sql, new RowMapper<Rn_Dashbord>() {

			@Override
			public Rn_Dashbord mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Dashbord rn_dashbord_t = new Rn_Dashbord();
	
				rn_dashbord_t.setPending_claims(rs.getInt("pending_claims"));
				rn_dashbord_t.setApproved_claims(rs.getInt("approved_claims"));
				rn_dashbord_t.setTotal_claims(rs.getInt("total_claims"));
				rn_dashbord_t.setRejected_claims(rs.getInt("rejected_claims"));
				System.out.println(rs.getInt("approved_claims")+"approved claims");

				return rn_dashbord_t;
			}
			
		});
		
		return rn_dashbord;
	}
	
	
	@Override
	public List<Rn_Dashbord> DashbordDealerCount_List(int user_id) {
		String sql = "SELECT PENDING_SR, SUBMITTED_SR, CONVERTED_SR, TOTAL_SR, "
				+ "APPROVED_CLAIMS, REJECTED_CLAIMS, INPROCESS_CLAIMS, TOTAL_CLAIMS "
				+ "FROM (SELECT COUNT(SRH.SERVICE_REQUEST_ID) PENDING_SR "
				+ "FROM rn_service_request_header_t SRH, rn_distributor_master_t KDM "
				+ "WHERE SRH.SERVICED_BY = KDM.DISTRIBUTOR_ID AND SRH.SERVICE_REQUEST_STATUS = 'Created' "
				+ "AND KDM.USER_ID = "+user_id+")as PENDING_SR, "
				+ "(SELECT COUNT(SRH.SERVICE_REQUEST_ID) SUBMITTED_SR "
				+ "FROM rn_service_request_header_t SRH, rn_distributor_master_t KDM "
				+ "WHERE SRH.SERVICED_BY = KDM.DISTRIBUTOR_ID AND SRH.SERVICE_REQUEST_STATUS = 'Submitted' "
				+ "AND KDM.USER_ID = "+user_id+")as SUBMITTED_SR, "
				+ "(SELECT COUNT(SRH.SERVICE_REQUEST_ID) CONVERTED_SR "
				+ "FROM rn_service_request_header_t SRH, rn_distributor_master_t KDM "
				+ "WHERE SRH.SERVICED_BY = KDM.DISTRIBUTOR_ID AND SRH.SERVICE_REQUEST_STATUS = 'Claimed' "
				+ "AND KDM.USER_ID = "+user_id+")as CONVERTED_SR, "
				+ "(SELECT COUNT(SRH.SERVICE_REQUEST_ID) TOTAL_SR "
				+ "FROM rn_service_request_header_t SRH, rn_distributor_master_t KDM "
				+ "WHERE SRH.SERVICED_BY = KDM.DISTRIBUTOR_ID AND KDM.USER_ID = "+user_id+")as TOTAL_SR, "
				+ "(SELECT COUNT(KCH.CLAIM_HEADER_ID) APPROVED_CLAIMS "
				+ "FROM rn_claim_header_t KCH, rn_distributor_master_t KDM "
				+ "WHERE KCH.SERVICED_BY = KDM.DISTRIBUTOR_ID AND KCH.CLAIM_STATUS = 'Approved' "
				+ "AND KDM.USER_ID = "+user_id+")as APPROVED_CLAIMS, "
				+ "(SELECT COUNT(KCH.CLAIM_HEADER_ID) REJECTED_CLAIMS "
				+ "FROM rn_claim_header_t KCH, rn_distributor_master_t KDM "
				+ "WHERE KCH.SERVICED_BY = KDM.DISTRIBUTOR_ID AND KCH.CLAIM_STATUS = 'Rejected' "
				+ "AND KDM.USER_ID = "+user_id+")as REJECTED_CLAIMS, "
				+ "(SELECT COUNT(KCH.CLAIM_HEADER_ID) INPROCESS_CLAIMS "
				+ "FROM rn_claim_header_t KCH, rn_distributor_master_t KDM "
				+ "WHERE KCH.SERVICED_BY = KDM.DISTRIBUTOR_ID AND KCH.CLAIM_STATUS = 'In Process' "
				+ "AND KDM.USER_ID = "+user_id+")as INPROCESS_CLAIMS, "
				+ "(SELECT COUNT(KCH.CLAIM_HEADER_ID) TOTAL_CLAIMS "
				+ "FROM rn_claim_header_t KCH, rn_distributor_master_t KDM "
				+ "WHERE KCH.SERVICED_BY = KDM.DISTRIBUTOR_ID AND KDM.USER_ID = "+user_id+")as TOTAL_CLAIMS";
		List<Rn_Dashbord> listContact = jdbcTemplate.query(sql, new RowMapper<Rn_Dashbord>() {

			@Override
			public Rn_Dashbord mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Dashbord aContact = new Rn_Dashbord();
	
				aContact.setPending_srdealer(rs.getInt("PENDING_SR"));;
				aContact.setSubmitted_srdealer(rs.getInt("SUBMITTED_SR"));;
				aContact.setConverted_srdealer(rs.getInt("CONVERTED_SR"));;
				aContact.setTotal_srdealer(rs.getInt("TOTAL_SR"));;
				aContact.setApproved_claimsdealer(rs.getInt("APPROVED_CLAIMS"));;
				aContact.setRejected_claimsdealer(rs.getInt("REJECTED_CLAIMS"));;
				aContact.setInprocess_claimsdealer(rs.getInt("INPROCESS_CLAIMS"));;
				aContact.setTotal_claimsdealer(rs.getInt("TOTAL_CLAIMS"));;
				return aContact;
			}
			
		});
		
		return listContact;
	}
	

	@Override
	public List<Rn_Dashbord> DashbordSalesCount_List(int user_id) {
		String sql = "SELECT PENDING_SR, SUBMITTED_SR, CONVERTED_SR, TOTAL_SR, APPROVED_CLAIMS, REJECTED_CLAIMS, INPROCESS_CLAIMS, TOTAL_CLAIMS"
				+ " FROM (SELECT COUNT(SERVICE_REQUEST_ID) PENDING_SR FROM rn_service_request_header_t WHERE SERVICE_REQUEST_STATUS = 'Claimed')as PENDING_SR, "
				+ "(SELECT COUNT(SERVICE_REQUEST_ID) SUBMITTED_SR FROM rn_service_request_header_t WHERE SERVICE_REQUEST_STATUS = 'Submitted')as SUBMITTED_SR, "
				+ "(SELECT COUNT(SERVICE_REQUEST_ID) CONVERTED_SR FROM rn_service_request_header_t WHERE SERVICE_REQUEST_STATUS = 'Claimed')as CONVERTED_SR, "
				+ "(SELECT COUNT(SERVICE_REQUEST_ID) TOTAL_SR FROM rn_service_request_header_t)as TOTAL_SR, (SELECT COUNT(CLAIM_HEADER_ID) APPROVED_CLAIMS "
				+ "FROM rn_claim_header_t WHERE CLAIM_STATUS = 'Approved')as APPROVED_CLAIMS, (SELECT COUNT(CLAIM_HEADER_ID) REJECTED_CLAIMS FROM rn_claim_header_t "
				+ "WHERE CLAIM_STATUS = 'Rejected')as REJECTED_CLAIMS, (SELECT COUNT(CLAIM_HEADER_ID) INPROCESS_CLAIMS FROM rn_claim_header_t WHERE CLAIM_STATUS ="
				+ "'In Process')as INPROCESS_CLAIMS, (SELECT COUNT(CLAIM_HEADER_ID) TOTAL_CLAIMS FROM rn_claim_header_t)as TOTAL_CLAIMS";
		List<Rn_Dashbord> listContact = jdbcTemplate.query(sql, new RowMapper<Rn_Dashbord>() {

			@Override
			public Rn_Dashbord mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Dashbord aContact = new Rn_Dashbord();
	
				aContact.setPending_srsales(rs.getInt("PENDING_SR"));
				aContact.setSubmitted_srsales(rs.getInt("SUBMITTED_SR"));
				aContact.setConverted_srsales(rs.getInt("CONVERTED_SR"));
				aContact.setTotal_srsales(rs.getInt("TOTAL_SR"));
				aContact.setApproved_claimssales(rs.getInt("APPROVED_CLAIMS"));
				aContact.setTotal_claimssales(rs.getInt("TOTAL_CLAIMS"));
				aContact.setRejected_claimssales(rs.getInt("REJECTED_CLAIMS"));
				aContact.setInprocess_claimssales(rs.getInt("INPROCESS_CLAIMS"));
				return aContact;
			}
			
		});
		
		return listContact;
	}
	
	
	
	@Override
	public List<Rn_Admin_Count> AdminCount_List() {
		String sql = "SELECT DIST.DISTRIBUTOR_NAME,ifnull(OPEN_SR.OPEN_SR, 0) OPEN_SR,ifnull(OPEN_CLAIMS.OPEN_CLAIMS, 0) OPEN_CLAIMS,ifnull(CLOSED_CLAIMS.CLOSED_CLAIMS, 0) CLOSED_CLAIMS,ifnull(REJECTED_CLAIMS.REJECTED_CLAIMS, 0) REJECTED_CLAIMS,ifnull(INSTALLED_PRF.INSTALLED_PRF, 0) INSTALLED_PRF FROM (SELECT  KDM.DISTRIBUTOR_ID,COUNT(SRH.SERVICE_REQUEST_ID) OPEN_SR FROM rn_distributor_master_t KDM, rn_service_request_header_t SRH WHERE KDM.DISTRIBUTOR_ID = SRH.SERVICED_BY AND SRH.SERVICE_REQUEST_STATUS = 'Submitted' GROUP BY KDM.DISTRIBUTOR_ID) as OPEN_SR, (SELECT  KDM.DISTRIBUTOR_ID, COUNT(KCH.CLAIM_HEADER_ID) OPEN_CLAIMS FROM rn_distributor_master_t KDM, rn_claim_header_t KCH WHERE KDM.DISTRIBUTOR_ID = KCH.SERVICED_BY AND KCH.CLAIM_STATUS = 'Entered' GROUP BY KDM.DISTRIBUTOR_ID)as OPEN_CLAIMS, (SELECT  KDM.DISTRIBUTOR_ID, COUNT(KCH.CLAIM_HEADER_ID) CLOSED_CLAIMS FROM rn_distributor_master_t KDM, rn_claim_header_t KCH WHERE KDM.DISTRIBUTOR_ID = KCH.SERVICED_BY AND KCH.CLAIM_STATUS = 'Approved' GROUP BY KDM.DISTRIBUTOR_ID) as CLOSED_CLAIMS, (SELECT  KDM.DISTRIBUTOR_ID, COUNT(KCH.CLAIM_HEADER_ID) REJECTED_CLAIMS FROM rn_distributor_master_t KDM, rn_claim_header_t KCH WHERE KDM.DISTRIBUTOR_ID = KCH.SERVICED_BY AND KCH.CLAIM_STATUS = 'Rejected' GROUP BY KDM.DISTRIBUTOR_ID) as REJECTED_CLAIMS, (SELECT  KDM.DISTRIBUTOR_ID, COUNT(KID.INSTANCE_ID) INSTALLED_PRF FROM rn_distributor_master_t KDM, RN_INSTANCE_DETAILS_T KID WHERE KDM.DISTRIBUTOR_ID = KID.SERVICED_BY AND KID.INSTANCE_STATUS = 'Installed' GROUP BY KDM.DISTRIBUTOR_ID)as INSTALLED_PRF, rn_distributor_master_t DIST WHERE DIST.DISTRIBUTOR_ID = OPEN_SR.DISTRIBUTOR_ID AND DIST.DISTRIBUTOR_ID = OPEN_CLAIMS.DISTRIBUTOR_ID AND DIST.DISTRIBUTOR_ID = CLOSED_CLAIMS.DISTRIBUTOR_ID AND DIST.DISTRIBUTOR_ID = REJECTED_CLAIMS.DISTRIBUTOR_ID AND DIST.DISTRIBUTOR_ID = INSTALLED_PRF.DISTRIBUTOR_ID";

		List<Rn_Admin_Count> listContact = jdbcTemplate.query(sql, new RowMapper<Rn_Admin_Count>() {

			@Override
			public Rn_Admin_Count mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Admin_Count aContact = new Rn_Admin_Count();
	
				aContact.setDistributor_name(rs.getString("DISTRIBUTOR_NAME"));
				aContact.setOpen_claim(rs.getInt("OPEN_CLAIMS"));
				aContact.setOpen_sr(rs.getInt("OPEN_SR"));
				aContact.setClosed_claim(rs.getInt("CLOSED_CLAIMS"));
				aContact.setRejected_claim(rs.getInt("REJECTED_CLAIMS"));
				aContact.setInstalled_prf(rs.getInt("INSTALLED_PRF"));
		
				return aContact;
			}
			
		});
		
		return listContact;
	}
	}


