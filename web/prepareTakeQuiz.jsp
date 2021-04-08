<%-- 
    Document   : member
    Created on : 14-Jan-2021, 9:44:08 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Student</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head>
    <body>
        <header id="header"><!--header-->
            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="logo pull-left">
                                <a href="#"><img src="#" alt="" /></a>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="shop-menu pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a> Hello, ${sessionScope.ACCOUNT.fullName}</a></li>
                                    <li><a href="student.jsp"> Home</a></li>
                                    <li><a href="LoadQuizList"> Do Quiz</a></li>
                                    <li><a href="LoadQuizResult">Quiz Result</a></li>
                                    <li><a href="LogOut"> Log Out</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->

            <div class="shopper-informations">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="shopper-info">
                            <form>
                            </form>
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="blog-post-area">
                        </div>
                    </div>
                </div>					
            </div>
        </header><!--/header-->

        <section id="form"><!--form-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-1">
                        <div class="login-form"><!--login form-->
                            <c:if test="${empty sessionScope.TAKEQUIZLIST}">
                                <div class="alert alert-success" role="alert">
                                    There are no relevant quiz for you. Please update more questions or create new quiz.
                                </div>
                            </c:if>
                            <c:if test="${not empty sessionScope.TAKEQUIZLIST}">
                                <h2>Choose quiz you want to do:</h2>
                                <form action="TakeQuiz" method="POST">
                                    <select name="txtChooseQuiz">
                                        <c:forEach items="${sessionScope.TAKEQUIZLIST}" var="o">
                                            <c:choose>
                                                <c:when test="${o.subjectName.equals(param.txtChooseQuiz)}">
                                                    <option selected="" value="${o.quizId}">${o.quizName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${o.quizId}">${o.quizName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                    <br/>
                                    
                                    <button type="submit" name="action" value="TakeQuiz" class="btn btn-default">Do Quiz</button>
                                </form>
                            </c:if>
                        </div><!--/login form-->
                    </div>
                </div>
            </div>
        </section><!--/form-->

        <footer id="footer"><!--Footer-->
            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights reserved.</p>
                        <p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
                    </div>
                </div>
            </div>
        </footer><!--/Footer-->
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>

</html>
