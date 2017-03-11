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
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/skins/_all-skins.min.css">
         <link rel="stylesheet" href="/OBESystem/js/background.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- Main content -->
                <form action="EncodePO" method="post" name="encodePO">
                    <input type="hidden" name="contributor" class="readonlyWhite" id="contributor" value="${login.userID}" />
                    <section class="content">
                        <div class="box box-success">
                            <!-- /.box-header -->
                            <div class="box-header">
                                <h3 class="box-title">Program Outcome</h3>
                                <a href="#" id="po-info" data-toggle="popover" data-trigger="focus" data-placement="auto">
                                <button type="button" class="btn btn-primary btn-xs"><i class="fa fa-question"></i></button>
                                </a>
                                <a href="#" id="po-instruc" data-toggle="popover" data-trigger="focus" data-placement="auto">
                                    <button type="button" class="btn btn-success btn-xs"><i class="fa fa-info"></i></button>
                                </a>
                                <br>
                                <h5>Program: <span id = "program-title"></span></h5>
                                <input class="hidden" id="hidden-program-title" name="program-title">
                                <h5>College: <span id = "college-title" ></span></h5>
                                <input class="hidden" id="hidden-college-title" name="college-title">
                                <br>
                            </div>
                            <div class="box-body table-responsive">
                                <table id="data" class="table table-hover">
                                    <tr>
                                        <th id="po-code">Code</th>
                                        <th id="po-title">
                                            <div class="col-sm-10">
                                                Program Outcome
                                            </div>
                                        </th>
                                        <th id="po-mapping">
                                            <div class="col-sm-10">
                                                PA Mapping
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
                                <button id="addRowButton" type="button" class="btn btn-success pull-left"><i class="fa fa-plus"></i> Add Row</button>
                                <a href="/OBESystem/RedirectToSearchPO"><button type="button" class="btn btn-default pull-right">Cancel</button></a>
                                <button id ="save" type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#myModal">Save</button>
                            </div>
                            <!-- /.box-footer -->
                        </div>
                        <div id="myModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Confirmation Message</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure you want to save this? You can no longer <b>delete</b> or <b>change the mapping</b> of your entry once saved.</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Review entry</button>
                                        <button type="submit" class="btn btn-success">Yes, I am sure</button>
                                    </div>
                                </div>
                            </div>
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
        <script src="/OBESystem/js/store_program_search.js"></script>
        <script src="/OBESystem/js/create_PO.js"></script>
         <script src="/OBESystem/js/instructions.js"></script>
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
