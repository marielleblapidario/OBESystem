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
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/skins/_all-skins.min.css">
         <link rel="stylesheet" href="/OBESystem/js/background.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- Main content -->
                <form action="EncodeCO" method="post" name="EncodeCO">
                    <input type="hidden" name="contributor" class="readonlyWhite" id="contributor" value="${login.userID}" />
                    <section class="content">
                        <div class="box box-info">
                            <!-- /.box-header -->
                            <div class="box-header">
                                <h3 class="box-title">Course Outcome</h3><br>
                                <h5>Course: <span id="course-title"></span></h5>
                                <input class="hidden" id="hidden-codeCourse" name="codeCourse">
                                <br>

                                <label class="col-sm-2 control-label">Approver</label>
                                <div class="col-sm-10">
                                    <select name="select-approver" id="select-approver" class="form-control select2 select2-hidden-accessible" style="width: 30%;" tabindex="-1" aria-hidden="true">
                                    </select>
                                </div>
                                <br>
                            </div>
                            <div class="box-body table-responsive">
                                <table id="data" class="table table-hover">
                                    <tr>
                                        <th>Code</th>
                                        <th>
                                            <div class="col-sm-10">
                                                Course Outcome
                                            </div>
                                        </th>
                                        <th>
                                            <div class="col-sm-10">
                                                Weight
                                            </div>
                                        </th>
                                        <th>
                                            <div class="col-sm-10">
                                                Status
                                            </div>
                                        </th>
                                        <th>
                                            <div class="col-sm-10">
                                                Remarks
                                            </div>
                                        </th>
                                        <th>Tools</th>
                                    </tr>
                                </table>
                            </div>
                            <div class="box-footer">  
                                <button type="button" id="addRowButton" class="btn btn-primary pull-left"><i class="fa fa-plus"></i> Add Row</button>
                                <button type="button" class="btn btn-default pull-right">Cancel</button>
                                <button type="submit" class="btn bg-green pull-right">Send for Approval</button>
                                <button type="submit" class="btn bg-light-blue pull-right">Save</button>
                            </div>
                            <!-- /.box-footer -->
                        </div>
                    </section>
                </form>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 2.2.3 -->
        <script src="/OBESystem/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="/OBESystem/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- Slimscroll -->
        <script src="/OBESystem/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="/OBESystem/resources/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="/OBESystem/resources/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="/OBESystem/resources/dist/js/demo.js"></script>
        <!--selfmade-->
        <script src="/OBESystem/js/store_course_search.js"></script>
        <script src="/OBESystem/js/view_approver_list.js"></script>
        <script src="/OBESystem/js/create_CO.js"></script>

    </body>
</html>
