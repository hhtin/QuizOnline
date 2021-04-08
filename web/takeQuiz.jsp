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
        <style>
            span{border: solid 1px #ACACAC; padding: 2px;}
        </style>
        <script language="javascript">
            var m = null; // Phút
            var s = null; // Giây

            var timeout = null; // Timeout
            window.onload = init;
            function init() {
                start();
            }
            function start()
            {
                /*BƯỚC 1: LẤY GIÁ TRỊ BAN ĐẦU*/
                if (m === null)
                {
                    m = parseInt(document.getElementById('m_val').value);
                    s = parseInt(document.getElementById('s_val').value);
                }

                /*BƯỚC 1: CHUYỂN ĐỔI DỮ LIỆU*/
                // Nếu số giây = -1 tức là đã chạy ngược hết số giây, lúc này:
                //  - giảm số phút xuống 1 đơn vị
                //  - thiết lập số giây lại 59
                if (s === -1) {
                    m -= 1;
                    s = 59;
                }

                // Nếu số phút = -1 tức là đã chạy ngược hết số phút, lúc này:
                //  - giảm số giờ xuống 1 đơn vị
                //  - thiết lập số phút lại 59


                // Nếu số giờ = -1 tức là đã hết giờ, lúc này:
                //  - Dừng chương trình
                if (m == -1) {
                    clearTimeout(timeout);
                    document.getElementById('QuizForm').submit();
                    alert('Hết giờ');

                    return false;
                }

                /*BƯỚC 1: HIỂN THỊ ĐỒNG HỒ*/
                document.getElementById('m').innerText = m.toString();
                document.getElementById('s').innerText = s.toString();

                /*BƯỚC 1: GIẢM PHÚT XUỐNG 1 GIÂY VÀ GỌI LẠI SAU 1 GIÂY */
                timeout = setTimeout(function () {
                    s--;
                    start();
                }, 1000);
            }
            
           

            function stop() {
                clearTimeout(timeout);
            }
        </script>
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

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Time</h2>
                            <div id="demo">
                                <input type="hidden" id="m_val" placeholder="Phút" value="${sessionScope.MINUTES}"/> <br/>
                                <input type="hidden" id="s_val" placeholder="Giây" value="${sessionScope.SECOND}"/>
                            </div>

                            <div>
                                <span id="m">Phút</span> :
                                <span id="s">Giây</span>
                            </div>

                            <div class="brands_products"><!--brands_products-->

                            </div><!--/brands_products-->

                            <div class="price-range"><!--price-range-->

                            </div><!--/price-range-->

                            <div class="shipping text-center"><!--shipping-->

                            </div><!--/shipping-->
                        </div>
                    </div>
                    <div class="col-sm-9">
                        <div class="blog-post-area">
                            <h2 class="title text-center">Content</h2>
                            <div class="single-blog-post">
                                <form action="FinishQuiz" method="POST" id="QuizForm">
                                    <c:forEach items="${sessionScope.LISTQUESTIONTAKEQUIZ}" var="o" varStatus="count">
                                        <h3>${count.index+1}.${o.questionContent}</h3>
                                        <div>
                                            <input type="radio" id="A.${count.index}" name="answer${count.index}" value="A">
                                            <label for="A.${count.index}">A.${o.ansA}</label>
                                        </div>

                                        <div>
                                            <input type="radio" id="B.${count.index}" name="answer${count.index}" value="B">
                                            <label for="B.${count.index}">B.${o.ansB}</label>
                                        </div>

                                        <div>
                                            <input type="radio" id="C.${count.index}" name="answer${count.index}" value="C">
                                            <label for="C.${count.index}">C.${o.ansC}</label>
                                        </div>

                                        <div>
                                            <input type="radio" id="D.${count.index}" name="answer${count.index}" value="D">
                                            <label for="D.${count.index}">D.${o.ansD}</label>
                                        </div>

                                    </c:forEach>
                                        <button class="btn btn-primary" id="ButtonForm"  type="submit" name="action" value="FinishQuiz" class="btn btn-default">Finish</button>
                                </form>
                            </div>
                        </div>
                    </div>
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
    </body>

</html>
