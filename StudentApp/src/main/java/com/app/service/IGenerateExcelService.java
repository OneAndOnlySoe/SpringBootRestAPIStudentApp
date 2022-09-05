package com.app.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.app.entity.Student;

public interface IGenerateExcelService {
	
	ByteArrayOutputStream generateExcelFile(List<Student> list)throws IOException;
}
