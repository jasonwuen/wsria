/**
 * 首页Javascript
 *
 * @author 咖啡兔
 */
$(function(){
    $.gritter.add({
        title: '小二，来客人了，招呼起来……',
		image: 'http://www.wsria.com/wp-content/uploads/ria.jpg',
        text: '欢迎来到www.wsria.com配套演示系统',
		time: 100000,
		sticky: true,
		before_close: function() {
			alert('你轻轻的点击鼠标关闭我，但是正如刷新后我还会再来！^_^');
		}
    });
});
