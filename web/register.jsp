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
        <title>Guess</title>
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
        <form action="MainController" method="POST">
            <header id="header"><!--header-->
                <div class="header-middle"><!--header-middle-->
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="logo pull-left">
                                    <a href="#"><img src="#" alt="" /></a>
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

            <form action="#">
            </form>
            <section id="form"><!--form-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4 col-sm-offset-1">
                            <div class="login-form"><!--login form-->
                                <h2>Register New Account</h2>
                                <form action="Register" method="POST">
                                    Email:<input type="email" name="txtEmail" placeholder="Email" value="${param.txtEmail}" required/>
                                    <font color="red">${requestScope.INVALID.emailError}</font>
                                    <br/>
                                    
                                    Password:<input type="password" name="txtPassword" placeholder="Password" required/>
                                    <font color="red">${requestScope.INVALID.passwordError}</font>
                                     <br/>
                                    RePassword:<input type="password" name="txtRePassword" placeholder="RePassword" required/>
                                    <font color="red">${requestScope.INVALID.passwordError}</font>
                                    <br/>
                                    
                                    FullName:<input type="text" name="txtFullName" placeholder="Full Name" value="${param.txtFullName}" required/><br/>
                                    
                                    PhoneNumber:<input type="text" name="txtPhoneNumber" placeholder="Phone Number" value="${param.txtPhoneNumber}" required/>
                                    <font color="red">${requestScope.INVALID.phoneNumberError}</font>
                                    <br/>
                                    Gentle:
                                    <select name="txtSex">
                                        <c:choose>
                                            <c:when test="${param.txtSex == 'Woman'}">
                                                <option>Man</option>
                                                <option selected="">Woman</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option selected="">Man</option>
                                                <option>Woman</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                    <br/>
                                    <c:if test="${not empty requestScope.FALSEMSG}">
                                        <div class="alert alert-danger" role="alert">
                                            ${requestScope.FALSEMSG}
                                        </div>
                                    </c:if>
                                    <a href="login.jsp">Back To Login</a>
                                    <button type="submit" name="action" value="Register" class="btn btn-default">Register</button>
                                </form>



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
