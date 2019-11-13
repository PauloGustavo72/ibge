package com.apiibge.service;

import javax.servlet.http.HttpServletResponse;

public interface CsvService {

    HttpServletResponse getAllCitiesFormatted(HttpServletResponse response);
}
