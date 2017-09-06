$(function () {
    var $head = $('#head'), $list = $('#list');
    $head.display({displayed: function () {$('#list').find('a[data-display-auto]').trigger('click');}});
    $list.display({
        selector: 'a', activeClass: 'active', target: '#con', trigger: 'click', targetZIndex: 'none',
        name: 'list', show: function () {$('#loading').show();}, shown: function () {$('#loading').hide();}
    });
});

var Tip = {
    _dis: new $.Display({display: 'messager'}),
    show: function(msg, succ) {
        Tip._dis.show({content: msg?msg:'操作失败', type: succ?'success':'danger'})
    }
};