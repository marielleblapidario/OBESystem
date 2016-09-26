<%@page import="model.IGA"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<jsp:include page ="navbar.jsp" />
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/skins/_all-skins.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
        <script>
            $(document).ready(
                    
                    function () {
                        var count = 0;
                        var lastCodeIGA = '${codeIGA}';
                        console.log(lastCodeIGA);
                        if (lastCodeIGA) {
                            count = parseFloat(lastCodeIGA.replace(/[^0-9\.]+/g, ''));
                        }
                        console.log(count);
                        $('#addRowButton').click(function () {
                            var newCodeIGA;
                            count += 1;
                            if (count > 9){
                                newCodeIGA = "IGA" + count;
                            } else {
                                newCodeIGA = "IGA0" + count;
                            }
                            console.log(newCodeIGA);
                            $('#data').append(
                                    '<tr>' +
                                    '<td>'+ newCodeIGA +'</td>' +
                                    '<input type="hidden" name="codeIGA" class="readonlyWhite" id="codeIGA" value="'+ newCodeIGA +'" />' +
                                    '<td>' +
                                    '<div class="col-sm-10">' +
                                    '<input type="text" name="description" class="form-control no-border" id="description" placeholder="Enter Institutional Graduate Attribute">' +
                                    '</div>' +
                                    '</td>' +
                                    '<td>' +
                                    '<div class="col-sm-10">' +
                                    '<input type="text" name="remarks" class="form-control no-border" id="remarks">' +
                                    '</div>' +
                                    '</td>' +
                                    '<td>' +
                                    '<button type="button" class="btn btn-success btn-xs"><i class="fa fa-edit"> </i></button>' +
                                    '<button type="button" id="deleteRow" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i></button>' +
                                    '</td>' +
                                    '</tr>'
                                    );
                        });
                        $(document).on('click', '#deleteRow', function (event) {
                            count -= 1;
                            $(this).closest('tr').remove()
                        });
                    }
            );
        </script> 
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- Main content -->
                <form action="EncodeIGA" method="post" name="createIGA">
                    <section class="content">
                        <div class="box box-info">
                            <!-- /.box-header -->
                            <div class="box-header">
                                <h3 class="box-title">Institutional Graduate Attribute</h3><br>
                            </div>
                            <!--hidden values -->
                            <input type="hidden" name="contributor" class="readonlyWhite" id="contributor" value="${login.userID}" />
                            <!--encoding IGA table-->
                            <div class="box-body table-responsive">
                                <table id= "data" class="table table-hover">
                                    <tr>
                                        <th>Code</th>
                                        <th>
                                            <div class="col-sm-10">
                                                Institutional Graduate Attribute
                                            </div>
                                        </th>
                                        <th>
                                            <div class="col-sm-10">
                                                Remarks
                                            </div>
                                        </th>
                                        <th>Tools</th>
                                    </tr>
                                    <% ArrayList<IGA> listIGA = (ArrayList<IGA>) request.getAttribute("listIGA");
                                        String codeIGA = (String) request.getAttribute("codeIGA");

                                        for (int i = 0; i < listIGA.size(); i++) {
                                            if (!listIGA.isEmpty()) {
                                    %>
                                    <tr>
                                        <td>
                                            <%= listIGA.get(i).getCodeIGA()%>
                                            <input type="hidden" name="codeIGA" class="readonlyWhite" id="codeIGA" value="<%= listIGA.get(i).getCodeIGA()%>" />
                                        </td>
                                        <td>
                                            <div class="col-sm-10">
                                                <input type="text" name="description" class="form-control no-border" id="description" value ="<%= listIGA.get(i).getDescription()%>">
                                            </div> 
                                        </td>
                                        <td>
                                            <div class="col-sm-10">
                                                <input type="text" name="remarks" class="form-control no-border" id="remarks" value ="<%= listIGA.get(i).getRemarks()%>">
                                            </div>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-success btn-xs"><i class="fa fa-edit"></i></button>
                                            <button type="button" id="deleteRow" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                </table>
                            </div>
                            <div class="box-footer">  
                                <button id ="addRowButton" type="button" class="btn btn-primary pull-left"><i class="fa fa-plus"></i> Add Row</button>
                                <button type="button" class="btn btn-default pull-right">Cancel</button>
                                <button id ="save" type="submit" class="btn btn-success pull-right">Save</button>
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
        <!-- Delete Row -->
        <script src="/OBESystem/resources/bootstrap/js/deleteRow.js"></script>
    </body>
</html>
