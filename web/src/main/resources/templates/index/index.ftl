<!doctype html>
<html lang="zh">
<head>
    <#include "/common/head.ftl" />
</head>
<body class="sticky-header">
    <section>
        <#include "/common/left.ftl" />

        <!-- main content start-->
        <div class="main-content" >
            <!-- header section start-->
            <div class="header-section">

                <!--toggle button start-->
                <a class="toggle-btn"><i class="fa fa-bars"></i></a>
                <!--toggle button end-->

                <!--search start-->
                <form class="searchform" action="index.html" method="post">
                    <input type="text" class="form-control" name="keyword" placeholder="Search here..." />
                </form>
                <!--search end-->
            </div>

            <!--body wrapper start-->
            <div class="wrapper">
                <div class="row states-info">
                    <div class="col-md-3">
                        <div class="panel red-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <i class="fa fa-money"></i>
                                    </div>
                                    <div class="col-xs-8">
                                        <span class="state-title"> 公司数 </span>
                                        <h4>$ 23,232</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel blue-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <i class="fa fa-tag"></i>
                                    </div>
                                    <div class="col-xs-8">
                                        <span class="state-title">  职位数  </span>
                                        <h4>2,980</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel green-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <i class="fa fa-gavel"></i>
                                    </div>
                                    <div class="col-xs-8">
                                        <span class="state-title">  职位分类  </span>
                                        <h4>5980</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel yellow-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <i class="fa fa-eye"></i>
                                    </div>
                                    <div class="col-xs-8">
                                        <span class="state-title">  后台爬取任务  </span>
                                        <h4>10,000</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <section class="panel">
                            <header class="panel-heading">
                                职位搜索
                            </header>
                            <div class="panel-body">
                                <form action="/index/search" method="get">
                                    <div class="input-group m-bot15 col-md-8 container">
                                        <input type="text" class="form-control">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="button">Go!</button>
                                        </span>
                                    </div>
                                </form>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <#include "/common/bottom.ftl" />
</body>
</html>