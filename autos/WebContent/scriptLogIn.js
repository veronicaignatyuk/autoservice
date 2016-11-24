	
$(document).ready(function(){
  $('#login-trigger').click(function(){
    $(this).next('#login-content').slideToggle();
    $(this).toggleClass('active');          
    
    if ($(this).hasClass('active')) $(this).find('span').html('Log in &#x25B2;')
      else $(this).find('span').html('Log in &#x25BC;')
    })
});
$(document).ready(function(){
$('#feedback-mark').click(function(){
	$('#feedback-mark').hide();
	$('#feedback-content').hide();
	$('#feedback-mark1').show();
})
$('#feedback-mark1').click(function(){
	$('#feedback-mark1').hide();
	$('#feedback-mark').show();
	$('#feedback-content').show();
})});
