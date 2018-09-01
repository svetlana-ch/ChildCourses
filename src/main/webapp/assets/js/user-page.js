$(document).ready(function () {
    /* Переменная-флаг для отслеживания того, происходит ли в данный момент ajax-запрос. В самом начале даем ей значение false, т.е. запрос не в процессе выполнения */
    var inProgress = false;
    /* С какой статьи надо делать выборку из базы при ajax-запросе */
    var startFrom = 0;
    var firstTime = true;

    function firstLoad() {
        $.ajax({
            /* адрес файла-обработчика запроса */
            url: '/LikeIT/',
            /* метод отправки данных */
            type: 'POST',
            /* данные, которые мы передаем в файл-обработчик */
            data: {
                'last-question-number': startFrom,
                command: 'users-next-questions'
            },
            dataType: 'json',
            beforeSend: function () {
                inProgress = true;
            },
            success: function (msg) {
                if (msg.length > 0) {
                    $.each(msg, function (index, msg) {
                        $(document.getElementById('list')).append('<li class="list-group-item"><span class="tag tag-success tag-pill align-middle rating-def">' + msg.rating + ' </span> &nbsp <a class="align-baseline" href="/LikeIT?command=load-question&id=' + msg.id.toString() + '">' + msg.shortDescription + '</a></li>');
                    });
                    inProgress = false;
                    // Увеличиваем на 10 порядковый номер статьи, с которой надо начинать выборку из базы
                    startFrom += msg.length;
                    firstTime = false;
                    if(msg.length<10) {
                        $('#more').hide();
                    }
                    addInfo();
                }
            }
        });
    }

    if (firstTime === true) {
        firstLoad();
    }

    $("#question-list li").length < 10 ? inProgress = false : inProgress = true;

    $('#sidebar-questions').bind('click', (function () {
        $('#list').empty();
        $('#more').show();
        firstTime = true;
        startFrom = 0;
        firstLoad();
    }));

    $('#more').bind('click', (function () {
        if (!inProgress) {
            inProgress = false;
            $('#loading').show();
            $.ajax({
                /* адрес файла-обработчика запроса */
                url: '/LikeIT/',
                /* метод отправки данных */
                type: 'POST',
                /* данные, которые мы передаем в файл-обработчик */
                data: {
                    'last-question-number': startFrom,
                    command: 'users-next-questions'
                },
                dataType: 'json',
                beforeSend: function () {
                    inProgress = true;
                },
                success: function (msg) {
                    if (msg.length > 0) {
                        $.each(msg, function (index, msg) {
                            $(document.getElementById('list')).append('<li class="list-group-item"><span class="tag tag-success tag-pill align-middle rating-def">' + msg.rating + ' </span> &nbsp <a class="align-baseline" href="/LikeIT?command=load-question&id='+ msg.id.toString() + '">'+ msg.shortDescription + '</a></li>');
                        });
                        inProgress = false;
                        addInfo();
                        // Увеличиваем на 10 порядковый номер статьи, с которой надо начинать выборку из базы
                        startFrom += msg.length;
                        if(msg.length<10) {
                            $('#more').hide();
                        }
                    }
                }
            });
        }
    }));

    $('#sidebar-answers').bind('click', function answersClicked() {

        $('#list').empty();

        /* Переменная-флаг для отслеживания того, происходит ли в данный момент ajax-запрос. В самом начале даем ей значение false, т.е. запрос не в процессе выполнения */
        var inProgress = false;
        /* С какой статьи надо делать выборку из базы при ajax-запросе */
        var startFrom = 0;

        if (!inProgress) {
            inProgress = false;
            $('#loading').show();
            $.ajax({
                /* адрес файла-обработчика запроса */
                url: '/LikeIT/',
                /* метод отправки данных */
                type: 'POST',
                /* данные, которые мы передаем в файл-обработчик */
                data: {
                    'last-answer-number': startFrom,
                    command: 'users-next-answers'
                },
                dataType: 'json',
                beforeSend: function () {
                    inProgress = true;
                },
                success: function (msg) {
                    if (msg.length > 0) {
                        $.each(msg, function (index, msg) {
                            $(document.getElementById('list')).append('<li class="list-group-item"><span class="tag tag-success tag-pill align-middle rating-def">' + msg.rating + ' </span> &nbsp <a class="align-baseline" href="/LikeIT?command=load-question&id='+ msg.id.toString() + '">'+ msg.text + '</a></li>');
                        });
                        inProgress = false;
                        addInfo();
                        // Увеличиваем на 10 порядковый номер статьи, с которой надо начинать выборку из базы
                        startFrom += msg.length;
                        if(msg.length<10) {
                            $('#more').hide();
                        }
                    }
                }
            });
        }

        $("#list li").length < 10 ? inProgress = false : inProgress = true;


        $('#more').bind('click', (function () {
            if (!inProgress) {
                inProgress = false;
                $('#loading').show();
                $.ajax({
                    /* адрес файла-обработчика запроса */
                    url: '/LikeIT/',
                    /* метод отправки данных */
                    type: 'POST',
                    /* данные, которые мы передаем в файл-обработчик */
                    data: {
                        'last-answer-number': startFrom,
                        command: 'users-next-answers'
                    },
                    dataType: 'json',
                    beforeSend: function () {
                        inProgress = true;
                    },
                    success: function (msg) {
                        if (msg.length > 0) {
                            $.each(msg, function (index, msg) {
                                $(document.getElementById('list')).append('<li class="list-group-item"><span class="tag tag-success tag-pill align-middle rating-def">' + msg.rating + ' </span> &nbsp <a class="align-baseline" href="/LikeIT?command=load-question&id='+ msg.id.toString() + '">'+ msg.text + '</a></li>');
                            });
                            inProgress = false;
                            addInfo();
                            // Увеличиваем на 10 порядковый номер статьи, с которой надо начинать выборку из базы
                            startFrom += msg.length;
                            if(msg.length<10) {
                                $('#more').hide();
                            }
                        }
                    }
                });
            }
        }));
    });
});


