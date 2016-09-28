var table = $("#data");
var programCode = sessionStorage.getItem("programCode");

$(document).ready(function () {
    getCollegeByProgram(programCode);
    $('#addRowButton').click(function () {
        addNewRow();
    });
    $(document).on('click', '#deleteRow', function (event) {
        $(this).closest('tr').remove();
    });
});

function getCollegeByProgram(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPA?SelectedProgram=" + program,
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

function addRow(data) {
    var codePA = data.codePA;
    var description = data.description;
    var status = data.status;
    var remarks = data.remarks;

    table.append(
            '<tr>' +
            '<td>' + codePA + '</td>' +
            '<input type="hidden" name="codePA" class="readonlyWhite" id="codeIGA" value="' + codePA + '" />' +
            '<td>' +
            '<div class="col-sm-10">' +
            '<input type="text" name="description" class="form-control no-border" id="description" value="' + description + '">' +
            '</div>' +
            '</td>' +
            '<td>' +
            '<span class="label label-success">' + status + '</span>' +
            '<input type="hidden" name="status" class="readonlyWhite" id="codeIGA" value="' + status + '" />' +
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

function addNewRow() {
    table.append(
            '<tr>' +
            '<td>' +
            '<input type="text" name="codePA" class="form-control no-border" id="codeIGA" placeholder="PA01" />' +
            '<td>' +
            '<div class="col-sm-10">' +
            '<input type="text" name="description" class="form-control no-border" id="description">' +
            '</div>' +
            '</td>' +
            '<td>' +
            '<span class="label label-success"> pending </span>' +
            '<input type="hidden" name="status" class="readonlyWhite" id="codeIGA" value="pending" />' +
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


}