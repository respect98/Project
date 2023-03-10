package com.obj.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class ObjDtc {
	
	
	@GetMapping("/objdtcform")
	public ModelAndView objdtcform() {
		return new ModelAndView("management/obj_dtc");
	}
	
	@PostMapping(value="/objdtc",produces = "application/json; charset=UTF-8")
	public Map<String,String> dtcResult(@RequestParam("image") MultipartFile file,HttpSession ses){
		StringBuffer reqStr = new StringBuffer();
        String clientId = "okq9td6ec2";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "YuuRUcKiyN75J3p8xUVSBU5jjlDYnLjI0M5nbeLC";//애플리케이션 클라이언트 시크릿값";
        Map<String,String> map=new HashMap<>();
        String path=ses.getServletContext().getRealPath("/file");
        File dir=new File(path);
        if(!dir.exists()) {
        	dir.mkdirs();//업로드할 file 디렉토리 생성
        }
        //첨부한 원본파일명 알아내기
        String fname=file.getOriginalFilename();
        File imgF=new File(path, fname);
        try {
        	file.transferTo(imgF);//업로드 처리
        	
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = imgF.getAbsolutePath();
            File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision-obj/v1/detect"; // 객체 인식
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                map.put("result", response.toString());
                
            } else {
                System.out.println("error !!!");
                map.put("result", "error!!!");
            }
        } catch (Exception e) {
            System.out.println(e);
            map.put("result", "error: "+e.getMessage());
            e.printStackTrace();
        }
        return map;
	}
	
}
