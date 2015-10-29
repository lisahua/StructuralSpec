(function(){
    er = encodeURIComponent;
    var url =   '?ua=' + er(window.navigator.userAgent) +
                '&an=' + er(window.navigator.appName) +
                '&re=' + er(document.referrer) +
                '&sh=' + er(screen.height) +
                '&sw=' + er(screen.width) +
                '&os=' + er(navigator.oscpu) +
                '&pl=' + er(navigator.platform) +
                '&hr=' + er(window.location.href) +
                '&si=' + '1';

    var img = document.createElement('img');
    img.src = '//portfold.com/track/' + url;
    img.style.cssText = 'display:none;'
    img.width = '1';
    img.height = '1';
    var src = document.getElementsByTagName('script')[0];
    src.appendChild(img);
})();
    