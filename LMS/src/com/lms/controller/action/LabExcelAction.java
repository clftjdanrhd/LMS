package com.lms.controller.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.lms.dao.LabListDAO;
import com.lms.dto.LabListVO;

public class LabExcelAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String url ="/main.do";
		
		   	LabListVO lVo = new LabListVO();
		   	
			ArrayList<LabListVO> list = LabListDAO.getInstance().ExcelDownLoad();

			Workbook xls = new HSSFWorkbook();

			ServletOutputStream sos = null;

			

			try {

				Sheet sheet = xls.createSheet("Excel");



				sheet.setColumnWidth(3, 5000);						//��Ʈ ��������  ù��°�� cell , �ι�°�� ũ��

				sheet.setColumnWidth(5, 5000);

				sheet.setColumnWidth(7, 5000);

				

		//		CellStyle style = xls.createCellStyle();

				

		//		style.setWrapText(true);

		//		style.setFillBackgroundColor(HSSFColor.BLUE.index);

		//		style.setFillForegroundColor(HSSFColor.RED.index);

		//		style.setFillPattern(CellStyle.BIG_SPOTS);

				

				Row row = null;

				Cell cell = null;

				

				row = sheet.createRow(1);

				

				cell = row.createCell(2);

				cell.setCellValue("�й�");
				
				
				
				cell = row.createCell(3);

				cell.setCellValue("���۽ð�");

		

				cell = row.createCell(4);

				cell.setCellValue("���۽ð�");

				

				cell = row.createCell(5);

				cell.setCellValue("�ݳ��ð�");
				
				
				
				cell = row.createCell(6);

				cell.setCellValue("����");


				for(int i = 0 ; i < list.size(); i++){

					

					LabListVO dto = list.get(i);

					row = sheet.createRow(i+2);					//���� ĭ

					

					cell = row.createCell(2);					//���� ĭ

					cell.setCellValue(dto.getUserNo());		

					

					cell = row.createCell(3);

					cell.setCellValue(dto.getUserName());

					

					cell = row.createCell(4);

					cell.setCellValue(dto.getStartTime());

					

					cell = row.createCell(5);

					cell.setCellValue(dto.getEndTime());
					
					
					
					cell = row.createCell(6);

					cell.setCellValue(dto.getState());


				}
				

				response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode("ExcelDownload.xls", "UTF-8"));

				response.setContentType("application/vnd.ms.excel");

				sos = response.getOutputStream();

				xls.write(sos);

				//xls.close();

				sos.flush();

				

			} catch (Exception e) {

				System.out.println(e.getMessage());

			} finally{

				if(sos != null) sos.close();

			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
	}

}
