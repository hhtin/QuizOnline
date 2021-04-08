<%-- 
    Document   : cart
    Created on : 16-Jan-2021, 12:46:53 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Member</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

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
                                    <li><a>Hello, ${ACCOUNT.fullName}</a></li>
                                    <li><a href="admin.jsp"> Home</a></li>
                                    <li><a href="createQuiz.jsp"> Create Quiz</a></li>
                                    <li><a href="LoadQuiz"> View Quiz</a></li>
                                    <li><a href="createQuestion.jsp"> Create Question</a></li>
                                    <li><a href="PagingQuestion"> Update Question</a></li>
                                    <li><a href="LogOut"> Log Out</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->
            <div class="header-bottom"><!--header-bottom-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-9">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-bottom-->
        </header><!--/header-->


        <section id="cart_items">
            <div class="container">
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="price">Quiz Name</td>
                                <td class="price">Limit Time</td>
                                <td class="price">Subject Name</td>
                                <td class="price">Number Question</td>
                                <td class="price">Time Start</td>
                                <td class="price">Time End</td>
                                <td class="price">Status</td>
                                <td></td>
                            </tr>
                        </thead>
                        <c:if test="${QUIZLIST != null}">
                            <tbody>
                                <c:forEach items="${QUIZLIST}" var="o">
                                <form action="#" method="POST">
                                    <tr>
                                        <td class="cart_price">
                                            <p>${o.quizName}</p>
                                        </td>
                                        <td class="cart_price">
                                            <p>${o.limitTime}</p>
                                        </td>
                                        <td class="cart_price">
                                            <p>${o.subjectName}</p>
                                        </td>
                                        <td class="cart_price">
                                            <p>${o.numberQuestion}</p>
                                        </td>
                                        <td class="cart_price">
                                            <p>${o.timeStart}</p>
                                        </td>
                                        <td class="cart_price">
                                            <p>${o.timeEnd}</p>
                                        </td>
                                        <td class="cart_price">
                                            <p>${o.status}</p>
                                        </td>
                                        <td class="cart_delete">
                                            <a class="cart_quantity_delete" href="ChangeStatus?quizId=${o.quizId}&status=${o.status}" onclick="if (confirm('Are you sure you want to change status?')) {
                                                        form.action = '/Config?pg=FIBiller&amp;cmd=delete';
                                                    } else {
                                                        return false;
                                                    }">Change Status</a>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </c:if>
                        <c:if test="${QUIZLIST == null}">
                            <tbody>
                                <tr>
                                    <td>
                                        <h2 class="title text-center">List is empty</h2>
                                    </td>
                                </tr>
                            </tbody>
                        </c:if>
                    </table>
                </div>

            </div>
        </section> <!--/#cart_items-->
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