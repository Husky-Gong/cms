package com.sxt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxt.common.Constant;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;


@WebServlet("/checkCode.do")
public class CheckCodeController extends HttpServlet{

	private static final long serialVersionUID = -1597171426922505140L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Generate authorization code
		CircleCaptcha createCircleCaptcha = CaptchaUtil.createCircleCaptcha(200, 36, 4, 20);
		// put authorization code into session
		req.getSession().setAttribute(Constant.KEY_CHECK_CODE, createCircleCaptcha.getCode());
		// deliver authorization code to main page
		createCircleCaptcha.write(resp.getOutputStream());
	}

	
	
}
