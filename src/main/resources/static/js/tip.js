const Tip = {
    _dis: new $.Display({display: 'messager'}),
    show: function(msg, succ) {
        Tip._dis.show({content: msg?msg:'操作失败', type: succ?'success':'danger'})
    }
};