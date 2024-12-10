<h1>Library Service</h1>
<hr/>
<h2>How to start app?</h2>

1. Install Docker Engine (I use v.27.1.1, build 6312585)
2. Clone the repository. Run commands:
```
    git clone <repository-url>
    cd library_service
```
3. Make sure the port 8080 and the port 5000 are free
4. Run command:
```
    docker-compose up 
```

Now you can send examples of requests into localhost:8080

<h2>How to use?</h2>

- Send request ```localhost:8080/auth/signup``` and ```localhost:8080/auth/login``` with your username and password (view example)
- You will receive a JWT token and will need input it into Postman request header.
- You can then send another requests.