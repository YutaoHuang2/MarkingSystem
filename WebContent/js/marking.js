$(document).ready(function(){
  var isDrawingLine = false;
  var zoomInFactor = 1.1;
  var zoomOutFactor = 1 / zoomInFactor;
  var zoomTime = 0;
  var maxTimes = 3;
  var defaultScore = 0.0;
  var papertype = 0;
  var fullScore;
  var paperId;
  var topicId;
  var detail;
  var simage = 'data:image/png;base64,';
  var canvas = new fabric.Canvas('canvas');
  //加载图片
  $.ajax({
    method: 'get',
    url: "../mark/getTopic",
    data: "topicNum=2&userId=1234",
    success: function(data) {
      simage += data.dir;
      fullScore = data.fullMark;
      paperId = data.paperId;
      topicId = data.topicNum;

      //在canvas中添加图片
      fabric.Image.fromURL(simage, function(oImg) {
        canvas.setWidth(oImg.width);
        canvas.setHeight(oImg.height);
        oImg.crossOrigin = "anonymous";
        oImg.selectable = false;
        oImg.hoverCursor = '11';
        canvas.add(oImg);
      });
    },
  });
  //阅卷细则表格
  var tbdata=[{"detail":"cccc","fullmark":"cccc","mark":"asdf","remark":""}];
  var dgs = $( '#dg' ).datagrid({
    data:tbdata,
    columns: [[{
        field: "detail",
        title: "细则",
        width:80
    },{
        field: "fullmark",
        title: "满分",
        width:80
    },{
        field: "mark",
        title: "打分",
        width:90,
        editor: {
          type: 'numberbox',
          options: {}
        }
    },{
        field: "remark",
        title: "备注",
        width:90,
        editor: {
          type: 'text',
          options: {}
        }
    }]],
    fitColumns:true
  });
  $('#dg').datagrid('beginEdit',0);

  //仲裁细则表格
  var tbdata=[{"detail":"cccc","fullmark":"cccc","first":"cccc","second":"cccc","arbitration":"cccc"}];
  var dgs = $( '#adg' ).datagrid({
    data:tbdata,
    columns: [[{
        field: "detail",
        title: "细则",
        width:80
    },{
        field: "fullmark",
        title: "满分",
        width:80
    },{
        field: "first",
        title: "一评",
        width:90,
        editor: {
          type: 'numberbox',
          options: {}
        }
    },{
        field: "second",
        title: "二评",
        width:90,
        editor: {
          type: 'numberbox',
          options: {}
        }
    },{
        field: "arbitration",
        title: "仲裁",
        width:90,
        editor: {
          type: 'numberbox',
          options: {}
        }
    }]],
    fitColumns:true
  });
  $('#adg').datagrid('beginEdit',0);

  //勾叉
  $('#checkbox').click(function() {
    isDrawingLine = false;
    fabric.Image.fromURL('../image/checkbox.png', function(oImg) {
      oImg.width = 60;
      oImg.height = 60;
      oImg.hoverCursor = '11';
      canvas.add(oImg);
    });
  })
  $('#cross').click(function() {
    isDrawingLine = false;
    fabric.Image.fromURL('../image/cross.png', function(oImg) {
      oImg.width = 60;
      oImg.height = 60;
      oImg.hoverCursor = '11';
      canvas.add(oImg);
    });
  })
  
  //试卷类型
  $('#questionable').click(function() {
    $('#questionable').attr("class", "btn btn-info");
    $('#blank').attr("class", "btn btn-default");
    $('#typical').attr("class", "btn btn-default");
    papertype = 0;
  })

  $('#typical').click(function() {
    $('#questionable').attr("class", "btn btn-default");
    $('#blank').attr("class", "btn btn-default");
    $('#typical').attr("class", "btn btn-info");    
    papertype = 1;
  })

  $('#blank').click(function() {
    $('#questionable').attr("class", "btn btn-default");
    $('#blank').attr("class", "btn btn-info");
    $('#typical').attr("class", "btn btn-default");    
    papertype = 2;
  })

  //分数按钮
  $('#zeropoints').click(function() {
    $('#score').val("0");
  })

  $('#fullpoints').click(function() {
    $('#score').val(fullScore);
  })
  
  //提交按钮
  $('#submit').click(function() {
    var dataURL = canvas.toDataURL({
      format: 'png',
      multiplier: 1 / 1.25
    });
    var dataU = dataURL.substring(22);
    var param = "userId=1234&time=0&paperId=" + paperId + "&topicId=" + topicId + "&point=3" + +"&image=" + dataU;
    console.log(dataU);
    $.ajax({
      method: 'post',
      url: "../mark/store",
      data: param,
      success: function(data) {},
    });
    defaultScore = 0.0;
  })
  
  //缩放
  $('#zoomout').click(function() {
    if (zoomTime > -maxTimes) {
      zoomTime -= 1;
      zoomIt(zoomOutFactor);
    }
  })

  $('#zoomin').click(function() {
    if (zoomTime < maxTimes) {
      zoomTime += 1;
      zoomIt(zoomInFactor);
    }
  })
  
  function zoomIt(factor) {
    canvas.setHeight(canvas.getHeight() * factor);
    canvas.setWidth(canvas.getWidth() * factor);
    if (canvas.backgroundImage) {
      var bi = canvas.backgroundImage;
      bi.width = bi.width * factor;
      bi.height = bi.height * factor;
    }
    var objects = canvas.getObjects();
    for (var i in objects) {
      var scaleX = objects[i].scaleX;
      var scaleY = objects[i].scaleY;
      var left = objects[i].left;
      var top = objects[i].top;    var tempScaleX = scaleX * factor;
      var tempScaleY = scaleY * factor;
      var tempLeft = left * factor;
      var tempTop = top * factor;    objects[i].scaleX = tempScaleX;
      objects[i].scaleY = tempScaleY;
      objects[i].left = tempLeft;
      objects[i].top = tempTop;    objects[i].setCoords();
    }
    canvas.renderAll();
    canvas.calcOffset();
  }
  
  //划线部分
  $('#blackline').click(function() {
    isDrawingLine = true;
  })
  
  var line, isDown;
  canvas.on('mouse:down', function(o) {
    if (!isDrawingLine)
      return;
    isDown = true;
    var pointer = canvas.getPointer(o.e);
    var points = [pointer.x, pointer.y, pointer.x, pointer.y];
    line = new fabric.Line(points, {
      strokeWidth: 1,
      fill: 'black',
      stroke: 'black',
      originX: 'center',
      originY: 'center'
    });
    canvas.add(line);
  });
  
  canvas.on('mouse:move', function(o) {
    if (!isDown || !isDrawingLine)
      return;
    var pointer = canvas.getPointer(o.e);
    line.set({
      x2: pointer.x,
      y2: pointer.y
    });
    canvas.renderAll();
  });
  
  canvas.on('mouse:up', function(o) {
    isDown = false;
    isDrawingLine = false;
  });
  
  //细则分数
  $(".btn").click(function() {
    var value = $(this).html();
    var id = $(this).parent().attr("id");
    $("input." + id).val(value);
    setScores();
  });
  
  $("input").blur(function() {
    setScores()
  })
  
  $("input.q1").blur(function() {
    if (parseFloat($("input.q1").val()) > 3)
      $("input.q1").val(3);
    if (parseFloat($("input.q1").val()) < 0)
      $("input.q1").val(0);
    setScores();
  })
  
  $("input.q2").blur(function() {
    if (parseFloat($("input.q2").val()) > 2)
      $("input.q2").val(2);
    setScores();
  })
  
  function setScores() {
    var scores = parseFloat($("input.q1").val()) + parseFloat($("input.q2").val());
    $("#score").html(scores);
  }
  
});