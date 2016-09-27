function addRow() {
    $('#addRowButton').on('click', function (e) {
        $.ajax({
            url: "EncodeIGA",
            type: 'POST',
            dataType: 'json',
            success: function () {
                $('#data').append(
                        '<tr>' +
                        '<td>IGA01</td>' +
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
            },
            error: function () {
                console.log('error');
            }
        });
    });
}
