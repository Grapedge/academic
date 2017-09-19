function s2ab(s) {
    if (typeof ArrayBuffer !== 'undefined') {
        var buf = new ArrayBuffer(s.length);
        var view = new Uint8Array(buf);
        for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
        return buf;
    } else {
        var buf = new Array(s.length);
        for (var i = 0; i != s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
        return buf;
    }
}

function Workbook() {
    if (!(this instanceof Workbook)) return new Workbook();
    this.SheetNames = [];
    this.Sheets = {};
}

function export_table_to_excel(arr, type, fn) {
    var ws = XLSX.utils.aoa_to_sheet(arr);
    var workBook = new Workbook();
    var sheetName = 'sheet 1';
    workBook.SheetNames.push(sheetName);
    workBook.Sheets[sheetName] = ws;
    var wbout = XLSX.write(workBook, {bookType: type, bookSST: true, type: 'binary'});
    var fname = fn || 'test.' + type;
    try {
        saveAs(new Blob([s2ab(wbout)], {type: "application/octet-stream"}), fname);
    } catch (e) {
        if (typeof console != 'undefined') console.log(e, wbout);
    }
    return wbout;
}

function doIt(arr, type, fn) {
    return export_table_to_excel(arr, type || 'xlsx', fn);
}