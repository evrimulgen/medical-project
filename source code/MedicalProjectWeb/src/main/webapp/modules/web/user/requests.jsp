<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 内容部分 --%>
<div class="padding">
	<div class="full col-sm-12">
		<div class="page-header">
			<h1>
				我的请求<small></small>
			</h1>
		</div>

		<div id="requests-wrap">
			<div class="col-sm-3  col-sm-offset-4 tab-wrap">
				<div class="btn-group btn-group-justified" role="group">
					<a href='#requests'
						class="btn btn-success" role="button">历史请求</a> <a
						href='#create-request'
						class="btn btn-default" role="button">发起请求</a>
				</div>
			</div>
			<div class="col-sm-12" id="req-history-list-wrap">
				<ul class="list-unstyled">
					<li>
						<div class="panel panel-primary case-wrap">
							<div class="panel-body">
								<div class="panel panel-primary case-item-wrap">
									<div class="panel-heading">
										<div class="row">
											<div class="col-sm-4 title-left">病例1 (张三)</div>
											<div class="col-sm-4 title-center">大脑</div>
											<div class="col-sm-4 title-right">共4张</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
										</div>
									</div>
									<div class="panel-footer">状态：正在诊断</div>
								</div>

								<div class="panel panel-primary case-item-wrap">
									<div class="panel-heading">
										<div class="row">
											<div class="col-sm-4 title-left">病例2 (李四)</div>
											<div class="col-sm-4 title-center">胸腔</div>
											<div class="col-sm-4 title-right">共2张</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
										</div>
									</div>
									<div class="panel-footer">状态：完成诊断</div>
								</div>
							</div>
							<div class="panel-footer">
								<div class="row">
									<div class="col-sm-6 left">2015-07-21 23:33</div>
									<div class="col-sm-6 right">
										<button type="button" class="btn btn-xs btn-link">查看报告</button>
									</div>
								</div>
							</div>
						</div>

					</li>

					<li>
						<div class="panel panel-primary case-wrap">
							<div class="panel-body">
								<div class="panel panel-primary case-item-wrap">
									<div class="panel-heading">
										<div class="row">
											<div class="col-sm-4 title-left">病例1 (张三)</div>
											<div class="col-sm-4 title-center">大脑</div>
											<div class="col-sm-4 title-right">共4张</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
										</div>
									</div>
									<div class="panel-footer">状态：正在诊断</div>
								</div>

								<div class="panel panel-primary case-item-wrap">
									<div class="panel-heading">
										<div class="row">
											<div class="col-sm-4 title-left">病例2 (李四)</div>
											<div class="col-sm-4 title-center">胸腔</div>
											<div class="col-sm-4 title-right">共2张</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
										</div>
									</div>
									<div class="panel-footer">状态：完成诊断</div>
								</div>
							</div>
							<div class="panel-footer">
								<div class="row">
									<div class="col-sm-6 left">2015-07-21 23:33</div>
									<div class="col-sm-6 right">
										<button type="button" class="btn btn-xs btn-link">查看报告</button>
									</div>
								</div>
							</div>
						</div>

					</li>

					<li>
						<div class="panel panel-primary case-wrap">
							<div class="panel-body">
								<div class="panel panel-primary case-item-wrap">
									<div class="panel-heading">
										<div class="row">
											<div class="col-sm-4 title-left">病例1 (张三)</div>
											<div class="col-sm-4 title-center">大脑</div>
											<div class="col-sm-4 title-right">共4张</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
										</div>
									</div>
									<div class="panel-footer">状态：正在诊断</div>
								</div>

								<div class="panel panel-primary case-item-wrap">
									<div class="panel-heading">
										<div class="row">
											<div class="col-sm-4 title-left">病例2 (李四)</div>
											<div class="col-sm-4 title-center">胸腔</div>
											<div class="col-sm-4 title-right">共2张</div>
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
											<div class="col-sm-2">
												<img src="" alt="" class="img-thumbnail case-img" />
											</div>
										</div>
									</div>
									<div class="panel-footer">状态：完成诊断</div>
								</div>
							</div>
							<div class="panel-footer">
								<div class="row">
									<div class="col-sm-6 left">2015-07-21 23:33</div>
									<div class="col-sm-6 right">
										<button type="button" class="btn btn-xs btn-link">查看报告</button>
									</div>
								</div>
							</div>
						</div>

					</li>
				</ul>


				<div class="col-sm-12">
					<nav>
						<ul class="pager">
							<li class="previous"><a href="#"><span
									aria-hidden="true">&larr;</span> 上一页</a></li>
							<li class="next"><a href="#">下一页 <span
									aria-hidden="true">&rarr;</span></a></li>
						</ul>
					</nav>
				</div>
			</div>

		</div>

	</div>
</div>
<!-- /padding -->

<%-- END 内容部分 --%>
<script src='<c:url value="/modules/web/assets/js/user/requests.js"/>'></script>
	