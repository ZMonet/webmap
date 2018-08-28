
//   //编辑图形
//
//    var polylineEditor =new AMap.PolyEditor(map,polyline);
// AMap.event.addDomListener(document.getElementById('startEdit'), 'click', function() {
//    polylineEditor.open();
// });
//
//
// //结束编辑
//
// AMap.event.addDomListener(document.getElementById('endEdit'),'click',function () {
//     polylineEditor.close();
// })


//移除
AMap.event.addDomListener(document.getElementById('delete'), 'click', function () {
    var overlaysList = map.getAllOverlays();
    console.log(pCount);
    if(pCount>0){
        map.remove(overlaysList[pCount - 1]);
        pSet.splice(pCount-1);
        pType.splice(pCount-1);
        messageInfo.splice(pCount-1);
        pCount--;
        console.log(pSet);
        console.log(pType);
        console.log(messageInfo);
    }
    else {
        alert("没有可移除的图形")
    }

}, false);





//查看已保存的图形
var TypeAndPaths=[];
AMap.event.addDomListener(document.getElementById('redraw'), 'click', function() {
    $.ajax({
        type : 'POST',
        url : "redrawAction.action",
        data:{},
        dataType:'json',
        Async:true,
        success:function(data) {
            if(data){
                //将json字符串转换为json对象，因为要通过点的方法来拿它的属性  eval('('+str+')');
                var obj = eval('(' + data + ')');
                TypeAndPaths = obj.allpath.split(";");
                for(var m in TypeAndPaths){
                    if("polygon" == TypeAndPaths[m].substring(0,7)){
                        var jobj = JSON.parse(TypeAndPaths[m].substring(8,TypeAndPaths[m].length));
                        var arr = [];
                        for(var k in jobj){
                            arr.push([jobj[k].lng,jobj[k].lat]);
                        }
                        var polygon = new AMap.Polygon({
                            path: arr,          //设置线覆盖物路径
                            fillColor: '#ffc162', // 多边形填充颜色
                            borderWeight: 2, // 线条宽度，默认为 1
                            strokeColor: '#ff9b76' // 线条颜色
                        });
                        map.add(polygon);
                    }
                    else if("polyline" == TypeAndPaths[m].substring(0,8)){
                        var jobj = JSON.parse(TypeAndPaths[m].substring(9,TypeAndPaths[m].length));
                        var arr = [];
                        for(var k in jobj){
                            arr.push([jobj[k].lng,jobj[k].lat]);
                        }
                        var polyline = new AMap.Polyline({
                            path: arr,        //设置线覆盖物路径
                            borderWeight: 3, // 线条宽度，默认为 1
                            strokeColor: '#0c21ff', // 线条颜色
                            lineJoin: 'round' // 折线拐点连接处样式
                        });
                        map.add(polyline);
                    }
                    else if("rectangle"==TypeAndPaths[m].substring(0,9)){
                        var jobj = JSON.parse(TypeAndPaths[m].substring(10,TypeAndPaths[m].length));
                        var arr=[];
                        for(var k in jobj){
                            arr.push([jobj[k].lng,jobj[k].lat]);
                        }
                        var polygon = new AMap.Polygon({
                            path:arr,   //覆盖物路径
                            strokeColor: '#d974ff',  //线颜色
                            strokeWeight: 3,      //线宽
                            strokeOpacity: 1,     //线透明度
                            strokeDasharray: [30, 10],   //补充线样式
                            strokeStyle: 'solid',  //线样式
                            fillColor: '#eca9ff',     //填充颜色
                            fillOpacity: 0.5,     //填充透明度
                        });
                        map.add(polygon);
                    }
                }
            }else{
                alert("传值为null");
            }
        },
        error:function (){
            alert("error");
        }
    });
}, false);