<%@page contentType="text/html;charset=UTF-8"%>
<!--
<header class="navbar navbar-inverse bs-docs-nav" role="banner">
-->
<header class="navbar navbar-inverse" role="navigation">

    <div class="container">
        <div class="navbar-header">
            <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="${ctx}/" class="navbar-brand hidden-sm">Hesine framework网</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${ctx}/">首页</a></li>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)">Portal function<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li class="">
                            <a href="${ctx}/portal/user/list">list</a>
                        </li>
                        <li class="">
                            <a href="${ctx}/portal/user/pagelist">pagelist</a>
                        </li>
                        <li class="">
                            <a href="${ctx}/portal/user/add">add</a>
                        </li>

                    </ul>
                </li>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)">Manager function<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li class="">
                            <a target="_blank" href="${ctx}/manager/user/list">list</a>
                        </li>
                        <li class="">
                            <a target="_blank" href="${ctx}/manager/user/add">add</a>
                        </li>
                    </ul>
                </li>
                <li><a target="_blank" href="#">Philps framework教程</a></li>
                <li><a target="_blank" href="#">网站实例</a></li>
                <li><a href="#">关于</a></li>
                <li><a href="${ctx}/user/logout">登出</a></li>
                <li><%=request.getSession().getAttribute("com.hesine.passport.user")%></li>

            </ul>
        </div>
    </div>
</header>