<!DOCTYPE html>
<jsp:include page ="security.jsp" />
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Simple Tables</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="/OBESystem/resources/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="/OBESystem/resources/downloads/font-awesome-4.7.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="/OBESystem/resources/downloads/ionicons-2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <!-- daterange picker -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/daterangepicker/daterangepicker.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/datepicker/datepicker3.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/iCheck/all.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <!-- Bootstrap time Picker -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/timepicker/bootstrap-timepicker.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/select2/select2.min.css">
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="resources/dist/css/skins/_all-skins.min.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <!-- ./wrapper -->
        <div class="wrapper">
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">
                    <div class="box box-success">
                        <div class="box-header with-border">
                            <h3 class="box-title">View Syllabus</h3>
                        </div>
                        <!-- /.box-header -->
                        <input type="hidden" name="contributor" class="readonlyWhite" id="contributor" value="${login.userID}" />
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Curriculum Followed</label>
                                <input type="hidden" name="mapCurID" class="readonlyWhite" id="hidden-mapCurID" />
                                <div  class="col-sm-10">
                                    <input name="curriculum-title" type="text" class="form-control" id="curriculum-title" readOnly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Course</label>
                                <div  class="col-sm-10">
                                    <input name="course-title" type="text" class="form-control" id="course-title" readOnly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Academic Start Year</label>
                                <div class="col-sm-10">
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <input name="startYear" type="text" class="form-control pull-right" id="startYear" readOnly>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Academic End Year</label>
                                <div class="col-sm-10">
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <input name="endYear" type="text" class="form-control pull-right" id="endYear" readOnly>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">Term</label>
                                <div  class="col-sm-10">
                                    <input name="term" type="text" class="form-control" id="term" readOnly>
                                </div>
                            </div>
                        </div>
                        <!-- Add CO -->
                        <div class="box box-info" id="div-addCO">
                            <div class="box-header with-border">
                                <h4 class="box-title">Course Outcomes</h4>
                            </div>
                            <div class="box-body table-responsive">
                                <table id="data" class="table table-hover">
                                    <tr>
                                        <th>Code</th>
                                        <th>Course Outcome</th>
                                        <th>Performance Indicator</th>
                                        <th>Remarks</th>
                                    </tr>
                                </table>
                                <br>
                            </div>
                        </div>
                        <!-- /add CO -->
                        <!-- Add Assessment -->
                        <div class="box box-info" id="div-addAssessment">
                            <div class="box-header with-border">
                                <h4 class="box-title">Assessments</h4>
                            </div>
                            <div class="box-body table-responsive">
                                <table id="data-assessment" class="table table-hover">
                                    <tr>
                                        <th>Code</th>
                                        <th>Assessment</th>
                                        <th>Course Outcome (CO)</th>
                                        <th>Description</th>
                                        <th>Weight of Assessment to CO</th>
                                    </tr>
                                </table>
                                <br>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <a href="/OBESystem/RedirectToViewSyllabusList"><button type="button" class="btn btn-default pull-right">Back</button></a>
                            </div>
                            <!-- /.box-footer -->
                        </div>
                    </div>
                    <!-- /add Assessment -->
                </section>
            </div>
        </div>
        <!-- jQuery 2.2.3 -->
        <script src="/OBESystem/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="/OBESystem/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- Select2 -->
        <script src="/OBESystem/resources/plugins/select2/select2.full.min.js"></script>
        <!-- InputMask -->
        <script src="/OBESystem/resources/plugins/input-mask/jquery.inputmask.js"></script>
        <script src="/OBESystem/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="/OBESystem/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <!-- date-range-picker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="/OBESystem/resources/plugins/daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap datepicker -->
        <script src="/OBESystem/resources/plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- bootstrap color picker -->
        <script src="/OBESystem/resources/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <!-- bootstrap time picker -->
        <script src="/OBESystem/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- SlimScroll 1.3.0 -->
        <script src="/OBESystem/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="/OBESystem/resources/plugins/iCheck/icheck.min.js"></script>
        <!-- FastClick -->
        <script src="/OBESystem/resources/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="/OBESystem/resources/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="/OBESystem/resources/dist/js/demo.js"></script>
        <!--self made-->
        <script src="/OBESystem/js/view_syllabus.js"></script>
    </body>
</html>
