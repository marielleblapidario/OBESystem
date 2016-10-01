var table = $("#data");
var programCode = sessionStorage.getItem("programCode");

$(document).ready(function () {
    getCollegeByProgram(programCode);
    getLastPA(programCode);
    $(document).on('click', '#deleteRow', function (event) {
        $(this).closest('tr').remove();
    });

});

function getCollegeByProgram(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPO?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addRow);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getLastPA(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetLastPO?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);

            var count = 0;
            var lastCodePO = data;
            console.log(lastCodePO);
            if (lastCodePO) {
            count = parseFloat(lastCodePO.replace(/[^0-9\.]+/g, ''));
            }
            console.log(count);
            $('#addRowButton').click(function () {
                var newCodePO;
                count += 1;
                if (count > 9) {
                    newCodePO = "PO" + count;
                } else {
                    newCodePO = "PO0" + count;
                }
                console.log(newCodePO);
                $('#data').append(
                        '<tr>' +
                        '<td>' + newCodePO + '</td>' +
                        '<input type="hidden" name="codePO" class="readonlyWhite" id="codePO" value="' + newCodePO + '" />' +
                        '<td>' +
                        '<div class="col-sm-10">' +
                        '<input type="text" name="description" class="form-control no-border" id="description" placeholder="Enter Institutional Graduate Attribute">' +
                        '</div>' +
                        '</td>' +
                        '<td>' +
                        '<span class="label label-success">pending</span>' +
                        '<input type="hidden" name="status" class="readonlyWhite" id="codeIGA" value="pending"/>' +
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
            },
                    error: function (response) {
                    console.log(response);
                }
    });
}

function addRow(data) {
    var codePO = data.codePO;
    var description = data.description;
    var status = data.status;
    var remarks = data.remarks;

    table.append(
            '<tr>' +
            '<td>' + codePO + '</td>' +
            '<input type="hidden" name="codePO" class="readonlyWhite" id="codePO" value="' + codePO + '" />' +
            '<td>' +
            '<div class="col-sm-10">' +
            '<input type="text" name="description" class="form-control no-border" id="description" value="' + description + '">' +
            '</div>' +
            '</td>' +
            '<td>' +
            '<span class="label label-success">' + status + '</span>' +
            '<input type="hidden" name="status" class="readonlyWhite" id="status" value="' + status + '" />' +
            '</td>' +
            '<td>' +
            '<div class="col-sm-10">' +
            '<input type="text" name="remarks" class="form-control no-border" id="remarks" value="' + remarks + '">' +
            '</div>' +
            '</td>' +
            '<td>' +
            '<button type="button" class="btn btn-success btn-xs"><i class="fa fa-edit"> </i></button>' +
            '<button type="button" id="deleteRow" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i></button>' +
            '</td>' +
            '</tr>'
            );
}