package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.franchisee.HistoricalFranReportDao;
import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;

@Service
@Transactional
public class HistoricalFranReportServiceImpl implements HistoricalFranReportService {

	@Autowired
	HistoricalFranReportDao historicalFranReportDao;
	
	@Override
	public List<FranchiseeAccountStatementDto> getHistoricalFranBalTransferStatement(
			Long userId, String fromDate, String toDate, String number) {
		List<FranchiseeAccountStatementDto> transactionTransportList ;//= new ArrayList<FranchiseeAccountStatementDto>();
		transactionTransportList = historicalFranReportDao.getHistoricalTransactionTransportBeanListRetailerByNumber(userId,
						fromDate, toDate, number);

		
		Collections.sort(transactionTransportList,
				new FranchiseeAccountStatementComparator());
		return transactionTransportList;
	
	}	
}
