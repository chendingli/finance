/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';

    // The toolbar groups arrangement, optimized for two toolbar rows.
    config.toolbarGroups = [
       { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
       { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
       { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
       { name: 'forms', groups: [ 'forms' ] },
       '/',
       { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
       { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
       { name: 'links', groups: [ 'links' ] },
       { name: 'insert', groups: [ 'insert' ] },
       '/',
       { name: 'styles', groups: [ 'styles' ] },
       { name: 'colors', groups: [ 'colors' ] },
       { name: 'tools', groups: [ 'tools' ] },
       { name: 'others', groups: [ 'others' ] },
       { name: 'about', groups: [ 'about' ] }
    ];

    // Remove some buttons provided by the standard plugins, which are
    // not needed in the Standard(s) toolbar.
    config.removeButtons = 'Underline,Subscript,Superscript,Flash,Save';

    // Set the most common block elements.
    config.format_tags = 'p;h1;h2;h3;pre';

    // Simplify the dialog windows.
    config.removeDialogTabs = 'image:advanced;link:advanced';

    config.width = 800; //宽度
    config.height = 400; //高度

    // 图片上传相关
    //  config.filebrowserImageUploadUrl = './upload'; // 图片上传路径
      config.image_previewText = ' '; // 图片信息面板预览区内容的文字内容，默认显示CKEditor自带的内容
      config.removeDialogTabs = 'image:advanced;image:Link'; // 移除图片上传页面的'高级','链接'页签

    config.font_names = '宋体/SimSun;新宋体/NSimSun;仿宋/FangSong;楷体/KaiTi;仿宋_GB2312/FangSong_GB2312;'+
       '楷体_GB2312/KaiTi_GB2312;黑体/SimHei;华文细黑/STXihei;华文楷体/STKaiti;华文宋体/STSong;华文中宋/STZhongsong;'+
       '华文仿宋/STFangsong;华文彩云/STCaiyun;华文琥珀/STHupo;华文隶书/STLiti;华文行楷/STXingkai;华文新魏/STXinwei;'+
       '方正舒体/FZShuTi;方正姚体/FZYaoti;细明体/MingLiU;新细明体/PMingLiU;微软雅黑/Microsoft YaHei;微软正黑/Microsoft JhengHei;'+
       'Arial Black/Arial Black;'+ config.font_names;

    config.allowedContent = true;
    config.pasteFromWordKeepsStructure = false;
    config.format_p = { element: 'p', attributes: { 'class': 'normalPara' } };
};
