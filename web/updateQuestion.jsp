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
                            <h2>UPDATE QUESTION</h2>
                            <form action="UpdateQuestion" method="POST">
                                <c:if test="${requestScope.QUESTIONINFO.questionContent != null}">
                                    Question Content:<input type="text" name="txtQuestionContent" placeholder="Question Content" value="${requestScope.QUESTIONINFO.questionContent}" required/>
                                    <br/>
                                </c:if>
                                <c:if test="${requestScope.QUESTIONINFO.questionContent == null}">
                                    Question Content:<input type="text" name="txtQuestionContent" placeholder="Question Content" value="${param.txtQuestionContent}" required/>
                                    <br/>
                                </c:if> 


                                <c:if test="${requestScope.QUESTIONINFO.ansA != null}">
                                    Answer A:<input type="text" name="txtAnsA" placeholder="Answer A" value="${requestScope.QUESTIONINFO.ansA}" required/>
                                    <br/>
                                </c:if>
                                <c:if test="${requestScope.QUESTIONINFO.ansA == null}">
                                    Answer A:<input type="text" name="txtAnsA" placeholder="Answer A" value="${param.txtAnsA}" required/>
                                    <br/>
                                </c:if> 

                                <c:if test="${requestScope.QUESTIONINFO.ansB != null}">
                                    Answer B:<input type="text" name="txtAnsB" placeholder="Answer B" value="${requestScope.QUESTIONINFO.ansB}" required/>
                                    <br/>
                                </c:if>
                                <c:if test="${requestScope.QUESTIONINFO.ansB == null}">
                                    Answer B:<input type="text" name="txtAnsB" placeholder="Answer B" value="${param.txtAnsB}" required/>
                                    <br/>
                                </c:if> 

                                <c:if test="${requestScope.QUESTIONINFO.ansC != null}">
                                    Answer C:<input type="text" name="txtAnsC" placeholder="Answer C" value="${requestScope.QUESTIONINFO.ansC}" required/>
                                    <br/>
                                </c:if>
                                <c:if test="${requestScope.QUESTIONINFO.ansC == null}">
                                    Answer C:<input type="text" name="txtAnsC" placeholder="Answer C" value="${param.txtAnsC}" required/>
                                    <br/>
                                </c:if> 

                                <c:if test="${requestScope.QUESTIONINFO.ansD != null}">
                                    Answer D:<input type="text" name="txtAnsD" placeholder="Answer D" value="${requestScope.QUESTIONINFO.ansD}" required/>
                                    <br/>
                                </c:if>
                                <c:if test="${requestScope.QUESTIONINFO.ansD == null}">
                                    Answer D:<input type="text" name="txtAnsD" placeholder="Answer D" value="${param.txtAnsD}" required/>
                                    <br/>
                                </c:if> 

                                    
                                Subject:
                                <c:if test="${requestScope.QUESTIONINFO.subjectName == null}">
                                    <select name="txtSubject">
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
                                    <br/>
                                </c:if>
                                <c:if test="${requestScope.QUESTIONINFO.subjectName != null}">
                                    <select name="txtSubject">
                                        <c:forEach items="${sessionScope.LISTSUBJECT}" var="o">
                                            <c:choose>
                                                <c:when test="${o.subjectName.equals(requestScope.QUESTIONINFO.subjectName)}">
                                                    <option selected="">${o.subjectName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option>${o.subjectName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                </c:if>
                                Correct Answer:
                                <c:if test="${requestScope.QUESTIONINFO.correctAns == null}">
                                    <select name="txtCorrectAnswer">
                                        <c:choose>
                                            <c:when test="${param.txtCorrectAnswer == 'B'}">
                                                <option>A</option>
                                                <option selected="">B</option>
                                                <option>C</option>
                                                <option>D</option>
                                            </c:when>

                                            <c:when test="${param.txtCorrectAnswer == 'C'}">
                                                <option>A</option>
                                                <option>B</option>
                                                <option selected="">C</option>
                                                <option>D</option>
                                            </c:when>

                                            <c:when test="${param.txtCorrectAnswer == 'D'}">
                                                <option>A</option>
                                                <option>B</option>
                                                <option>C</option>
                                                <option selected="">D</option>
                                            </c:when>

                                            <c:otherwise>
                                                <option selected="">A</option>
                                                <option>B</option>
                                                <option>C</option>
                                                <option>D</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                    <br/>
                                </c:if>
                                <c:if test="${requestScope.QUESTIONINFO.correctAns != null}">
                                    <select name="txtCorrectAnswer">
                                        <c:choose>
                                            <c:when test="${requestScope.QUESTIONINFO.correctAns == 'B'}">
                                                <option>A</option>
                                                <option selected="">B</option>
                                                <option>C</option>
                                                <option>D</option>
                                            </c:when>

                                            <c:when test="${requestScope.QUESTIONINFO.correctAns == 'C'}">
                                                <option>A</option>
                                                <option>B</option>
                                                <option selected="">C</option>
                                                <option>D</option>
                                            </c:when>

                                            <c:when test="${requestScope.QUESTIONINFO.correctAns == 'D'}">
                                                <option>A</option>
                                                <option>B</option>
                                                <option>C</option>
                                                <option selected="">D</option>
                                            </c:when>

                                            <c:otherwise>
                                                <option selected="">A</option>
                                                <option>B</option>
                                                <option>C</option>
                                                <option>D</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                    <br/>
                                </c:if>
                                <br/>

                                Status:
                                <c:if test="${requestScope.QUESTIONINFO.status == null}">
                                    <select name="txtStatus">
                                        <c:choose>
                                            <c:when test="${param.txtCorrectAnswer == false}">
                                                <option>True</option>
                                                <option selected="">False</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option selected="">True</option>
                                                <option>False</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                    <br/>
                                </c:if>
                                <c:if test="${requestScope.QUESTIONINFO.status != null}">
                                    <select name="txtStatus">
                                        <c:choose>
                                            <c:when test="${requestScope.QUESTIONINFO.status == false}">
                                                <option>True</option>
                                                <option selected="">False</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option selected="">True</option>
                                                <option>False</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                    <br/>
                                </c:if>
                                <br/>
                                <button type="submit" name="action" value="UpdateQuestion" class="btn btn-default">Update Question</button>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>

</html>
