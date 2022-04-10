package com.ssafy.happyhouse.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ssafy.happyhouse.model.HomeParamDto;

@RestController
@RequestMapping("/house")
public class HouseController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@GetMapping
	public ResponseEntity<String> getHouse(HomeParamDto homeParamDto) throws Exception{
				HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		StringBuilder result = new StringBuilder();	//numOfRows : 10 _ pageNo : 1
		String apiUrl="http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?" + 
				"serviceKey="+"ImX66xXJqCnENgLbcim7K0TWXVILTou4n9OI27ZrFlPDcZkV6jg4yxbjbtQWyy2J%2Fhp1I%2BtHqQooxTk4DkeUvw%3D%3D"+
				"&DEAL_YMD="+homeParamDto.getDEAL_YMD()+
				"&LAWD_CD="+homeParamDto.getLAWD_CD()+
				"&numOfRows=20";
		String answer = null;

		try {
			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }
            JSONObject jsonObject = XML.toJSONObject(result.toString());
            answer = jsonObject.toString();
            return new ResponseEntity<String>(answer, resHeaders, HttpStatus.OK);
            
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);	
	}

}
