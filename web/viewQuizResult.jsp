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
        <title>Admin</title>
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
                    <div class="col-sm-5 clearfix">
                        <div class="bill-to">
                            <p>Search Items</p>
                            <div class="form-one">
                                <form action="LoadQuizResult" method="POST">
                                    Subject:
                                    <select name="txtSubject">
                                        <option selected="">Show all Subject</option>
                                        <c:forEach items="${sessionScope.LISTSUBJECT}" var="o">
                                            <c:choose>
                                                <c:when test="${o.subjectName.equals(param.txtSubject)}">
                                                    <option selected="">${o.subjectName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option>${o.subjectName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <button class="btn btn-primary" type="submit" name="action" value="LoadQuizResult" class = "btn btn-default">Search</button>
                                </form>
                            </div>
                        </div>
                    </div>					
                </div>
            </div>
        </header><!--/header-->     

        <section>
            <div class="container">
                <div class="col-sm-11 padding-right">
                    <div class="features_items">
                        <h2 class="title text-center">QUESTION LIST</h2>
                        <c:forEach items="${sessionScope.QUIZRESULTLIST}" var="o">
                            <form action="PagingQuestion" method="POST">
                                <div class="col-sm-12">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <h2> Quiz ID:${o.quizName}</h2>
                                                <p>Num Of Correct Answer: ${o.numOfCorrectAns}</p>
                                                <p>Score: ${o.score}</p>
                                                <p>Time Start: ${o.timeStart}</p>
                                                <p>Time End: ${o.timeEnd}</p>
                                                <p>Subject: ${o.subjectName}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                    <nav style="margin: 20px auto; width: 500px" aria-label="...">
                        <ul class="pagination pagination-lg">
                            <c:forEach begin="1" end="${sessionScope.NUMBERPAGE}" var="i">
                                <li class="page-item">
                                    <a class="page-link" href="LoadQuizResult?index=${i}&txtSubject=${param.txtSubject}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </div>
        </section>

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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>

</html>
