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
        <!-- DataTables -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/datatables/dataTables.bootstrap.css">
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
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/skins/_all-skins.min.css">
         <link rel="stylesheet" href="/OBESystem/js/background.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <!-- ./wrapper -->
        <div class="wrapper">
            <div class="content-wrapper">
                <!-- Main content -->
                <form action="EncodeCurriculum" method="post" name="EncodeCurriculum">
                    <section class="content">
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title">Update Curriculum</h3>
                                <a href="#" id="curriculum-info" data-toggle="popover" data-trigger="focus" data-placement="auto">
                                <button type="button" class="btn btn-success btn-xs"><i class="fa fa-info"></i></button>
                                </a>                                
                            </div>
                            <!-- /.box-header -->
                            <input type="hidden" name="contributor" class="readonlyWhite" id="contributor" value="${login.userID}" />
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">Title</label>

                                    <div class="col-sm-10">
                                        <input name="title" type="text" class="form-control" id="inputEmail3" placeholder="BS in CSE 2010-2016" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Program</label>
                                    <div class="col-sm-10">
                                        <select name="select-program" id="select-program" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Academic Start Year</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input name="startYear" type="text" class="form-control pull-right" id="from" required>
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
                                            <input name="endYear" type="text" class="form-control pull-right" id="to" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-10">
                                        <textarea name="description" class="form-control" rows="3" placeholder="Enter ..." required></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Existing Curriculums</label>
                                    <div class="col-sm-10">
                                        <select id="select-import" class="form-control select2 select2-hidden-accessible" style="width: 90%;" tabindex="-1" aria-hidden="true">
                                        </select>
                                        <button id="button-import" type="button" class="btn btn-success pull-right"  style="width: 10%;"><i class="fa  fa-upload"></i> Import</button>
                                    </div>
                                </div>

                            </div>
                            <!-- Add Courses -->
                            <div class="box box-success">
                                <div class="box-header with-border">
                                    <h4 class="box-title">Courses in Curriculum</h4>
                                </div>
                                <div class="box-body no-border">

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Search Course</label>
                                        <div class="col-sm-10">
                                            <select id="select-course" class="form-control select2 select2-hidden-accessible" style="width: 90%;" tabindex="-1" aria-hidden="true">
                                            </select>
                                            <button id="button-add" type="button" class="btn btn-success pull-right" style="width: 10%;"><i class="fa fa-plus"></i> Add</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="box-body no-border">

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <h5 class="pull-right">Total Units:
                                                <span id = "total-units"></span>
                                            </h5>
                                        </div>
                                    </div>

                                    <div id="table">
                                        <table id="data" class="table table-hover">
                                            <tr>
                                                <th>Code</th>
                                                <th>Title</th>
                                                <th>Units</th>
                                                <th>Year Level</th>
                                                <th>Term</th>
                                                <th>Prerequisite</th>
                                                <th>Tool</th>
                                            </tr>
                                        </table>
                                    </div>
                                </div>

                            </div>
                            <!-- /Add Courses-->
                            <!-- /.box-body -->
                            <div class="box-footer">  
                                <a href="/OBESystem/ViewCurriculumList"><button type="button" class="btn btn-default pull-right">Cancel</button></a>
                                <button type="submit" class="btn btn-success pull-right">Confirm</button>
                            </div>
                            <!-- /.box-footer -->
                        </div>
                    </section>
                </form>
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
        <!-- DataTables -->
        <script src="/OBESystem/resources/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="/OBESystem/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
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
        <script src="/OBESystem/js/create_curriculum.js"></script>
        <script src="/OBESystem/js/search_program2.js"></script>
        <script src="/OBESystem/js/instructions.js"></script>
        <!-- Page script -->
        <script>
            $(function () {
                //Initialize Select2 Elements
                $(".select2").select2();

                //Datemask dd/mm/yyyy
                $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                //Datemask2 mm/dd/yyyy
                $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
                //Money Euro
                $("[data-mask]").inputmask();

                //self made
                $('#startYear').datepicker({
                    format: "yyyy",
                    autoclose: true,
                    minViewMode: "years"});

                $('#from').datepicker({
                    format: "yyyy",
                    autoclose: true,
                    minViewMode: "years"
                }).on('changeDate', function (selected) {
                    startDate = $("#from").val();
                    $('#to').datepicker('setStartDate', startDate);
                });
                ;


                $('#to').datepicker({
                    format: "yyyy",
                    autoclose: true,
                    minViewMode: "years"
                });

                //Date range picker
                $('#reservation').daterangepicker();
                //Date range picker with time picker
                $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
                //Date range as a button
                $('#daterange-btn').daterangepicker(
                        {
                            ranges: {
                                'Today': [moment(), moment()],
                                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                                'This Month': [moment().startOf('month'), moment().endOf('month')],
                                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                            },
                            startDate: moment().subtract(29, 'days'),
                            endDate: moment()
                        },
                        function (start, end) {
                            $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                        }
                );

                //Date picker
                $('#datepicker').datepicker({
                    autoclose: true
                });

                //iCheck for checkbox and radio inputs
                $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                    checkboxClass: 'icheckbox_minimal-blue',
                    radioClass: 'iradio_minimal-blue'
                });
                //Red color scheme for iCheck
                $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
                    checkboxClass: 'icheckbox_minimal-red',
                    radioClass: 'iradio_minimal-red'
                });
                //Flat red color scheme for iCheck
                $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                    checkboxClass: 'icheckbox_flat-green',
                    radioClass: 'iradio_flat-green'
                });

                //Colorpicker
                $(".my-colorpicker1").colorpicker();
                //color picker with addon
                $(".my-colorpicker2").colorpicker();

                //Timepicker
                $(".timepicker").timepicker({
                    showInputs: false
                });
            });
        </script>
    </body>
</html>
