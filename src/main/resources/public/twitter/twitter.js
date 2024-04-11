function addTweet() {
    var tweetInput = document.getElementById('tweetInput');
    var tweetText = tweetInput.value.trim();
    var tweetCreator = 'John Doe';

    if (tweetText !== '') {
        if (tweetText.length <= 140) {
            var url = 'https://2kjf5yqvmd.execute-api.us-east-1.amazonaws.com/beta?sender=' + tweetCreator + '&message=' + encodeURIComponent(tweetText);
            fetch(url, {
                method: 'POST'
            })
                .then(response => {
                    if (response.ok) {
                        // Crear y agregar el tweet a la lista
                        var tweet = document.createElement('li');
                        tweet.className = 'tweet';
                        tweet.innerHTML = '<p class="tweet-content">' + tweetText + '</p>' + '<p class="tweet-info"> Creador: ' + tweetCreator + '</p>';
                        document.getElementById('tweets').appendChild(tweet);
                        tweetInput.value = '';
                    } else {
                        alert('Error al enviar el tweet.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error al enviar el tweet.');
                });
        } else {
            alert('El mensaje no debe exceder los 140 caracteres.');
        }
    } else {
        alert('Por favor, escribe un mensaje antes de enviar.');
    }
}

function reloadPage() {
    fetch('https://2kjf5yqvmd.execute-api.us-east-1.amazonaws.com/beta/stream')
        .then(response => response.json())
        .then(data => {
            var tweetsList = document.getElementById('tweets');
            tweetsList.innerHTML = '';

            data.forEach(tweet => {
                var tweetElement = document.createElement('li');
                tweetElement.className = 'tweet';
                tweetElement.innerHTML = '<p class="tweet-content">' + tweet.message + '</p>' + '<p class="tweet-info"> Creador: ' + tweet.sender + '</p>';
                tweetsList.appendChild(tweetElement);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar los tweets.');
        });
}

function logout() {
    alert('Cerrando sesión...');
}