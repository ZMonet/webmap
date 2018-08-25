var map = new AMap.Map('container', {
    resizeEnable: true,
    zoom: 10  //级别
});


// 浏览器定位
map.plugin('AMap.Geolocation', function () {
    var geolocation = new AMap.Geolocation({
        enableHighAccuracy: true,  //是否使用高精度定位
        timeout: 10000,           //设置定位超时时间
        buttonOffset: new AMap.Pixel(10, 20), // 定位按钮的停靠位置的偏移量，默认：Pixel(10, 20)
        zoomToAccuracy: true,  //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        buttonPosition: 'RT'   //  定位按钮的排放位置,  RB表示右下, RT 右上角, LT 左上角, LB 左下角
    });
    map.addControl(geolocation);
    geolocation.getCurrentPosition();
    AMap.event.addListener(geolocation, 'complete', onComplete);
    AMap.event.addListener(geolocation, 'error', onError);
});

//定位成功
function onComplete(data) {
    var str = ["定位成功"];
    str.push('经度：' + data.position.getLng());     //push()方法用于向数组的末尾添加一个或多个元素，并返回新的长度
    str.push('纬度：' + data.position.getLat());
    if (data.accuracy) {
        str.push('精度：' + data.accuracy + '米');
    }
    str.push('是否经过偏移：' + (data.isCoverted ? '是' : '否'));
    document.getElementById('tip').innerHTML = str.join('<br>');
}

//定位失败
function onError() {
    document.getElementById('tip').innerHTML = '定位失败';
}

//绘制图形
var mouseTool = new AMap.MouseTool(map);
var currentType = '';
var elements = document.getElementsByClassName("draw-button");
for (var element of elements) {
    element.onclick = function (e) {
        currentType = e.target.id;
        switch (e.target.id) {
            case "polyline":
                mouseTool.polyline({
                    strokeColor: "#2782ff", //线颜色
                    strokeOpacity: 1,       //线透明度
                    strokeWeight: 3,        //线宽
                    strokeStyle: "solid",   //线样式
                    strokeDasharray: [10, 5] //补充线样式
                });
                break;
            case "rectangle":
                mouseTool.rectangle({
                    map: map,
                    strokeColor: '#FFC135',  //线颜色
                    strokeWeight: 3,      //线宽
                    strokeOpacity: 1,     //线透明度
                    strokeDasharray: [30, 10],   //补充线样式
                    strokeStyle: 'solid',  //线样式
                    fillColor: '#ffef50',     //填充颜色
                    fillOpacity: 0.5,     //填充透明度
                });
                break;
            case "polygon":
                mouseTool.polygon({
                    fillColor: '#53ecff', // 多边形填充颜色
                    fillOpacity: 0.8,//多边形填充透明度
                    strokeOpacity: 0.5, // 线条透明度
                    borderWeight: 8, // 线条宽度，默认为 1
                    strokeColor: '#b6a3ff', // 线条颜色
                    lineJoin: 'round'// 折线拐点连接处样式

                });
                break;
        }
    }
}


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



//获取图形的坐标
var polylineSet = [];    //所有线的点集
var polylineCount = 0;  //线的个数
var rectangleSet = [];    //所有矩形的点集
var rectangleCount = 0;  //矩形的个数
var polygonSet = [];    //所有多边形的点集
var polygonCount = 0;  //多边形的个数

AMap.event.addListener(mouseTool, 'draw', function (e) {
    var path = e.obj.getPath();    //获取坐标
    path.forEach(function (item) {
        delete item.O;
        delete item.M;
    })
    if (currentType == "polyline") {
        polylineSet[polylineCount] = path;   //把每条线的点集存到一个数组里
        console.log(polylineSet);
        polylineCount++;
    }
    else if (currentType == "rectangle") {
        rectangleSet[rectangleCount] = path;   //把每个矩形的点集存到一个数组
        console.log(rectangleSet);
        rectangleCount++;
    }
    else if(currentType=="polygon"){
        polygonSet[polygonCount] = path;   //把每个多边形的点集存到一个数组里
        console.log(polygonSet);
        polygonCount++;
    }
});


//删除
AMap.event.addDomListener(document.getElementById('delete'), 'click', function () {
    if(currentType=="polyline"){
        var overlaysLsit=map.getAllOverlays("polyline");
        var length = overlaysList.length;
        map.remove(overlaysList[length - 1]);
    }


    var overlaysList = map.getAllOverlays();
    var length = overlaysList.length;
    map.remove(overlaysList[length - 1]);
}, false);


//保存图形到数据库
AMap.event.addDomListener(document.getElementById("save"),'click',function () {
    $.ajax({
        type : 'POST',
        url :"saveAction.action",
        data: {
            "lpath":JSON.stringify(polylineSet),
            "rpath":JSON.stringify(rectangleSet),
            "gpath":JSON.stringify(polygonSet),
        },
        dataType:'json',
        success:function(data) {
            alert("success");
        },
        error:function (){
            alert("error");
        }
    });

    //将已保存到数据库的图形清空，以防多次保存
    polylineSet=[];
    polylineCount=0;
    rectangleSet=[];
    rectangleCount=0;
    polygonSet=[];
    polygonCount=0;

},false);



