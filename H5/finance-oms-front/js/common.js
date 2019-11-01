//$(function() {
/**************************************
 *@method reviwindow(移动端适配)
 *@param：无
 *@description ：
 * 引入js即可；
 *@author :sunpengwei
 ******************************************************/
function reviwindow() {
    var vieWidth = document.documentElement.clientWidth;
    var vieHeight = document.documentElement.clientHeight;
    if (vieWidth > 768) vieWidth = 768;
    document.documentElement.style.fontSize = vieWidth / 7.5 + 'px';
    // document.documentElement.style.height=vieHeight+'px';
}

reviwindow();
window.onresize = function () {
    reviwindow()
};
(function (window) {
    function Commonmethod() {
        return this === window && new Commonmethod();
    };


    Commonmethod.prototype = {
        baseUrl: function (baseurl) {
            var URL = {
                "baseurl": baseurl?baseurl:'http://210.22.8.76:9068/finance-oms',//外网
                // "baseurl": baseurl ? baseurl : 'http://localhost:8085/finance-oms',//本地
                "host":window.location.host
            }
            return URL
        },
        /**************************************
         *@method commonajax(公共ajax)
         *@param：    url,data,option,callback
         *@description ：
         *url:请求地址；
         *data：请求参数；
         *option:自定义配置；
         *callback回调函数
         * commonajax:基于jquery进行封装
         *@author :sunpengwei
         ******************************************************/
        ajax: function (url, data, option, callback) {
            var data = data || {}, header,
                _option = _option || {};
            var accessToken = sessionStorage.getItem("accessToken");
            if (accessToken) {
                header = {
                    "Authorization": accessToken
                }
            }
            var _option = {
                url: url,
                data: data,
                dataType: 'json',
                type: 'POST',
                headers: header,
                crossDomain: true,
                success: function (res) {
                    if (res.resultCode == '2024') { // token 失效重新登录
                        layer.msg(res.resultMsg);
                        setTimeout(function () {
                            window.parent.location.href = "login.html";
                        }, 500)
                        return;
                    }
                    if (res.resultCode != '0') {
                        layer.msg(res.resultMsg);
                        return;
                    }
                    callback && callback(res)
                }
            }
            $.extend(true, _option, option);
            $.ajax(_option);

        },

        /**************************************
         *@method formatTime(时间格式化)
         *@param：    value
         *@param：    value指的是需要格式化的中国标准时间；
         *@description ：
         * 格式化之后的格式为:yy-mm-dd hh:mm:ss;
         *@author :sunpengwei
         ******************************************************/
        formatTime: function (value) {
            // console.log(row.genTime);
            var time = new Date(value);
            var y = time.getFullYear(); //年
            var m = time.getMonth() + 1; //月
            var d = time.getDate(); //日
            var h = time.getHours(); //时
            var mm = time.getMinutes(); //分
            var s = time.getSeconds(); //秒
            h = h < 10 ? '0' + h : h;
            m = m < 10 ? '0' + m : m;
            d = d < 10 ? '0' + d : d;
            mm = mm < 10 ? '0' + mm : mm;
            s = s < 10 ? '0' + s : s;
            return (y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + s);
        },
        formatTimeDate: function (value) {
            // console.log(row.genTime);
            var time = new Date(value);
            var y = time.getFullYear(); //年
            var m = time.getMonth() + 1; //月
            var d = time.getDate(); //日
            var h = time.getHours(); //时
            var mm = time.getMinutes(); //分
            var s = time.getSeconds(); //秒
            h = h < 10 ? '0' + h : h;
            m = m < 10 ? '0' + m : m;
            d = d < 10 ? '0' + d : d;
            mm = mm < 10 ? '0' + mm : mm;
            s = s < 10 ? '0' + s : s;
            return (y + "-" + m + "-" + d );
        },
        formatTimeSeconds: function (value) {
            // console.log(row.genTime);
            var time = new Date(value);
            var y = time.getFullYear(); //年
            var m = time.getMonth() + 1; //月
            var d = time.getDate(); //日
            var h = time.getHours(); //时
            var mm = time.getMinutes(); //分
            var s = time.getSeconds(); //秒
            h = h < 10 ? '0' + h : h;
            m = m < 10 ? '0' + m : m;
            d = d < 10 ? '0' + d : d;
            mm = mm < 10 ? '0' + mm : mm;
            s = s < 10 ? '0' + s : s;
            return (y + "-" + m + "-" + d + " " + h + ":" + mm);
        },
        //将数字转换成金额显示
        formatMoney: function (num) {
            num = num.toFixed(2);
            return num;//返回的是字符串23,245.12保留2位小数
        },
        tips: function (text) {
            var tips_test = $('<div class="field-tooltipWrap"><div class="field-tooltipInner"><div class="field-tooltip fieldTipBounceIn"><div class="zvalid-resultformat">' + text + '</div></div></div></div>');
            tips_test.appendTo($("body"));
            setTimeout(function () {
                tips_test.remove();
            }, 1500);
        },
        /**************************************
         *@method fileimgage(上传照片)
         *@param：    sourceId:为input的id；targetId:目标id即img标签id;callback:回调函数
         *@description ：
         * 改方法基于jquery；传入inputid和目标id以及回调可以进行获取图片发送到服务器;
         *@author :sunpengwei
         ******************************************************/
        fileimgage: function (sourceId, targetId, callback) {
            var userAgent = null;
            userAgent = document.getElementById(sourceId).files[0];
            url = window.URL.createObjectURL(document.getElementById(sourceId).files[0]);
            var imgPre = document.getElementById(targetId);
            var $img = $("#" + targetId);
            if ($('#' + sourceId).siblings('.newfilepictruemessage')) {
                $('#' + sourceId).siblings('.newfilepictruemessage').hide();
            }
            $img.css({
                "max-width": '100%',
                'display': 'block',
                "max-height": '100%',
            });
            console.log(userAgent)
            imgPre.src = url;
            var baseurl = localStorage.getItem("baseurl");
            var formData = new FormData();
            formData.append('file', userAgent);
            callback && callback(formData)
        },
        /**************************************
         *@method Slidedelete(移动端滑动删除方法)
         *@param：    元素的classNane；
         *@param：    className指的是滑动元素的类名；
         *@description ：
         *该元素在css中需要使用绝对定位（即脱离文档流）拖拽涉及到的事件：
         *touchstart、touchmove、touchend；
         *建议样式：
         li.list-li {
                line-height: 60px;
                border-bottom: 1px solid #fcfcfc;
                position: relative;
                padding: 0 12px;
                color: #666;
                background: #f2f2f2;
                -webkit-transform: translateX(0px);
            }

         li .btn {
                position: absolute;
                top: 0;
                right: -80px;
                text-align: center;
                background: #ffcb20;
                color: #fff;
                width: 80px
            }
         *@author :sunpengwei
         ******************************************************/
        //h5页面滑动删除效果
        Slidedelete: function (objClass) {
            var initX, moveX;
            var X = 0;
            var objX = 0;
            $.each($("." + objClass), function (i, item) {
                $(item).bind('touchstart', function (e) {
                    var e = e || window.event;
                    e.stopPropagation();
                    if (this.className == objClass) {
                        initX = event.targetTouches[0].pageX;
                        objX = (this.style.WebkitTransform.replace(/translateX\(/g, "").replace(/px\)/g, "")) * 1;
                    }
                    if (objX == 0) {
                        $(item).bind('touchmove', function (e) {
                            var e = e || window.event;
                            e.preventDefault();
                            e.stopPropagation();
                            if (this.className == objClass) {
                                moveX = event.targetTouches[0].pageX;
                                X = moveX - initX;
                                if (X >= 0) {
                                    this.style.WebkitTransform = "translateX(" + 0 + "px)";
                                } else if (X < 0) {
                                    var l = Math.abs(X);
                                    this.style.WebkitTransform = "translateX(" + -l + "px)";
                                    if (l > 80) {
                                        l = 80;
                                        this.style.WebkitTransform = "translateX(" + -l + "px)";
                                    }
                                }
                            }
                        });
                    } else if (objX < 0) {
                        $(item).bind('touchmove', function (e) {
                            var e = e || window.event;
                            e.preventDefault();
                            e.stopPropagation();
                            if (this.className == objClass) {
                                moveX = event.targetTouches[0].pageX;
                                X = moveX - initX;
                                if (X >= 0) {
                                    var r = -80 + Math.abs(X);
                                    this.style.WebkitTransform = "translateX(" + r + "px)";
                                    if (r > 0) {
                                        r = 0;
                                        this.style.WebkitTransform = "translateX(" + r + "px)";
                                    }
                                } else { //向左滑动
                                    this.style.WebkitTransform = "translateX(" + -80 + "px)";
                                }
                            }
                        });
                    }
                })
                $(item).bind('touchend', function (e) {
                    var e = e || window.event;
                    e.stopPropagation();
                    if (this.className == objClass) {
                        objX = (this.style.WebkitTransform.replace(/translateX\(/g, "").replace(/px\)/g, "")) * 1;
                        if (objX > -40) {
                            this.style.WebkitTransform = "translateX(" + 0 + "px)";
                            objX = 0;
                        } else {
                            this.style.WebkitTransform = "translateX(" + -80 + "px)";
                            objX = -80;
                        }
                    }
                    this.removeEventListener('touchstart', this, false);
                    this.removeEventListener('touchmove', this, false);
                    this.removeEventListener('touchend', this, false);
                })
            })


        },
        /*****************************************
         *@method ： checkboxVal
         *@description ： 参数，第一个是多选框的name，第二个是val值，如果没有传入val值进来的时候则是获取所有多选框选中的数组，
         但是要求DOM中要的单选框中要设置name值。 如果有传入val匹配成功后则选中该多选框.
         *@param ：{String}name: 表单的name
         *@param ：{String}val：设置表单的val值 可选
         *@return ： val
         *@author ： sunpengwei
         *@createTime：
         *@modifyTime ：
         *************************************************************/

        checkboxVal: function (name, val) {
            var checkedarry = [];
            if (val === void 0) {
                $.each($('[name="' + name + '"]'), function (index, target) {
                    if (target.checked == true) {
                        val = target.value;
                        checkedarry.push(val)
                    }
                    ;

                })
                return checkedarry
            } else {
                $.each($('[name="' + name + '"]'), function (index, target) {
                    if (target.val == val) {
                        target.checked = true;

                    }
                    ;
                })
            }
        },

        /*************************
         *@method ：radiocheck
         *@description ： 参数，第一个是单选框的name，第二个是val值，如果没有传入val时则
         是获取当前单选框选中的val值，
         但是要求html中要的单选框中要设置val值。
         如果传入val则匹配成功后选中该单选框
         *@param ：{String}name: 表单的name
         *@param ：{String}val：设置表单的val值 可选
         *@return ： val
         *@author ： sunpengwei
         *@createTime：
         *@modifyTime ：
         ******************************************/
        radiocheck: function (name, val) {
            var a = '';
            if (val === void 0) {
                $each($('[name="' + name + '"]'), function (i, target) {
                    if (target.checked) {
                        a = target.value;
                    }
                    ;

                })

            } else {
                $each($('[name="' + name + '"]'), function (i, target) {
                    if (target.value == val) {
                        target.checked = true;
                    }
                    ;
                });

            }
            return a

        },
        /**************************
         *@method ：nameVal；
         *@param ： name、value(val值可选);
         *@description ：    根据name值获取对应的val值；val值可选；如有传入val值便可设置相应的val值
         *@author :pengwei
         *************************************/
        nameVal: function (name, val) {
            if (val === void 0) {
                return $('[name="' + name + '"]').val();
            } else {
                $('[name="' + name + '"]').val(val);
            }

        },
        /*********************************************
         *@method :formser;
         *@param :传入form表单的ID；
         *@description ： 表单序列化函数：获取相应的form表单内分数据；
         注意事项：需要在表单内设置相应的name值；
         *@author:sunpengwei
         *******************************************/
        formser: function (form) {
            var form = document.getElementById(form);
            var arr = {};
            for (var i = 0; i < form.elements.length; i++) {
                var feled = form.elements[i];
                switch (feled.type) {
                    case undefined:
                    case 'button':
                    case 'file':
                    case 'reset':
                    case 'submit':
                        break;
                    case 'checkbox':
                    case 'radio':
                        if (!feled.checked) {
                            break;
                        }
                    default:
                        if (arr[feled.name]) {
                            arr[feled.name] = arr[feled.name] + ',' + feled.value;
                        } else {
                            arr[feled.name] = feled.value;

                        }
                }
            }
            return arr
        },
        /****************************************
         *@method: getStyle;
         *@param : obj(获取的元素);attr(要获取的style属性)
         *@description:getstyle函数也可单独使用(可获取行内或者style内的样式);
         //使用范例：
         @@var sun=document.getElementById('sun');
         @@getstyle(sun,'left')
         @@getstyle函数可以获取元素的样式 ；
         使用范例：
         getstyle(obj,attr)attr为样式；obj为元素
         *@author:sunpengwei
         ***********************************************/
        getstyle: function (obj, attr) {
            return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj)[attr];
        },
        /******************************
         *@method: sortmethod;
         *@param : obj(需要进行属性排序的对象)propertyName:需要参照排序的属性
         *@description:sortmethod调用改方法时；需要传入排序对象和对象中参照排序属性
         *使用范例：
         data.sort(compare("age"));
         *@author:sunpengwei
         * ************************************************/
        //定义一个比较器
        sortmethod: function (obj, propertyName) {
            function compare(propertyName) {
                return function (object1, object2) {
                    var value1 = object1[propertyName];
                    var value2 = object2[propertyName];
                    if (value2 < value1) {
                        return 1;
                    } else if (value2 > value1) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }

            return obj.sort(compare(propertyName))
        },
        /***************************
         *@method: Maximum;
         *@param : ajs需要计算的数组
         *@description:传入需要计算出现次数最多的数值的数组即可
         *使用范例：
         Maximum([1,2,5,3,6,4,55,5,5,5]);
         *@author:sunpengwei
         * *********************************/
        Maximum: function (ajs) {
            var sun = {};
            for (var i = 0; i < ajs.length; i++) {
                var user = ajs[i];
                if (!sun[user]) {
                    sun[user] = 1;
                } else {
                    sun[user]++;
                }

            }
            return sun
        },
        /**************************************
         *@method Definetable(自定义表格包含分页)
         *@param：    option
         *@param：    option是配置对象,roletable:表格的lay-filter="roletable" id；detail预览回调；del删除回调；edit编辑回调;
         layevent:是事件对象数组；可以用以扩展事件格式：[{'evnetName':'del','evnetclick':fn}]
         *@description ：改方法基于layui；
         * 可以自行定义表格内容，也可通过传入配置进行设置相关属性;传入指定回调函数完成增删改查
         *@author:sunpengwei
         ******************************************************/
        Definetable: function (option, pageparam) {
            var cols = cols || [],
                _option = _option || {},
                url, _this = this;
            var _option = {
                elem: "",
                height: 500,
                url: "", //数据接口
                cols: [cols],
                method: "GET",
                cellMinWidth: 80,
                roletable: '',
                layevent: []
            }
            $.extend(true, _option, option);
            layui.use('table', function () {
                var tableobj = layui.table;
                _this.tableIns = tableobj.render(_option);
                tableobj.on('tool(' + _option.roletable + ')', function (obj) {
                    var data = obj.data;
                    for (var i = 0; i < _option.layevent.length; i++) {
                        if (obj.event === _option.layevent[i].evnetName) {
                            _option.layevent[i].evnetclick && _option.layevent[i].evnetclick(event, obj, _this.tableIns);
                        }
                    }
                });
                if (pageparam != void(0)) {
                    layui.use(['laypage', 'layer'], function () {
                        var laypage = layui.laypage;
                        var Urlparam = '';
                        //总页数大于页码总数
                        laypage.render({
                            elem: pageparam.elem,
                            count: pageparam.count, //数据总数
                            limit: 10,
                            jump: function (obj, first) {
                                var currentPage = obj.curr;
                                if (pageparam.Urlparam != void(0)) {
                                    for (var i in pageparam.Urlparam) {
                                        Urlparam += '&' + i + '=' + pageparam.Urlparam[i]
                                    }
                                }
                                //首次不执行
                                //if (!first) {
                                _this.ajax(pageparam.url + '?pageNum=' + currentPage + '&pageSize=' + pageparam.count + Urlparam, {}, {
                                    type: "GET",
                                    async: true
                                }, function (res) {
                                    debugger;
                                    if (res.resultCode == '0') {
                                        var list = res.data.accountList;
                                        for (var i = 0; i < _option.layevent.length; i++) {
                                            if (_option.layevent[i].evnetName == 'pagereload') {
                                                _option.layevent[i].evnetclick && _option.layevent[i].evnetclick(list, _this.tableIns);
                                                return false;
                                            }
                                        }
                                        _this.tableIns.reload({
                                            data: list
                                        });
                                    }
                                })
                                //}
                            }
                        });
                    });
                }
            });

        },
        /**************************************
         *@method chankWinopen(模拟window打开窗口)
         *@param： url
         *@param：    url即将打开窗口的url;
         *@description ：“”；
         *@author:sunpengwei
         ******************************************************/
        chankWinopen: function (option) {
            var _option = option || {};
            _option = {
                url: option.url,
                blank: '_blank'
            }
            $.extend(_option, option);
            window.open(_option.url, _option.blank);

        }
    }
    window['Commonmethod'] = Commonmethod;
})(window);

// 判断是否已经登录，没有登录直接跳转到登录页面
(function () {
    //if (window.location.href.indexOf("index.html")) {
    var accessToken = sessionStorage.getItem("accessToken");
    if (!accessToken && window.location.href.indexOf("login.html") == -1) {
        window.location.href = "login.html";
    }
    //}
})();

/**
 * 弹出框 _title抬头标题,_area_arr弹出框宽高数组,_id唯一性,_content弹出框内容,_btn_arr按钮数组,_funbtnxs点击按钮对象函数
 * @param _title
 * @param _area_arr
 * @param _id
 * @param _content
 * @param _btn_arr
 * @param _funbtnxs
 *
 *
 open("添加角色",null,"role-list","./role-add.html",["确定","取消"],
 {
     yes:function(index){

     },
     cancel:function(index){

     },
     success:function(index){

     }
 }
 );
 */
function layeropen(_title, _area_arr, _id, _content, _btn_arr, _funbtnxs) {
    if (!_area_arr) {
        //_area_arr = [($(window).width() * 0.9) + 'px', ($(window).height() - 50) + 'px'];
        _area_arr = [500 + 'px', ($(window).height() - 500) + 'px'];
    }
    layer.open({
        type: 2,
        title: _title,
        id: _id, //设定一个id，防止重复弹出'LAY_layuipro'
        btn: _btn_arr, //['确定', '取消']

        area: _area_arr,
        content: _content, //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        yes: _funbtnxs.yes,
        btn2: _funbtnxs.cancel,
        success: _funbtnxs.success
        /*yes:function(index, layero) { // 确定按钮
            var iframe = window['layui-layer-iframe' + index];
            iframe.app.submit();
            layer.close(index); // 关闭子窗口
        },
        cancel:function(index, layero){ // 关闭右上角

        },
        success: function (layero, index) {
            // 获取子页面的iframe
            // var iframe = window['layui-layer-iframe' + index];
            // 向子页面的全局函数child传参
            // iframe.child(id);
        }*/
    });
}

//})