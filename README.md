# Reactor + Spring Boot & React + Redux-Observable Starter Project

A starter using Spring-Boot and Reactor for backend, and using React, Redux, ES6 + JSX via Babel, and RxJS + Redux-Observable for frontend.
React structure follows [the Ducks pattern](https://github.com/erikras/ducks-modular-redux) — approach when each module's entry file (`feature-name.js`) contains all of its related constants, actions/action creators, selectors, its reducer and its epic which is the core primitive of redux-observable.

This starter is powered by the following technology stack:

- [x] [Spring Boot](https://projects.spring.io/spring-boot/)
- [x] [Reactor](https://projectreactor.io/)
- [x] [React](https://facebook.github.io/react/) and [JSX](https://facebook.github.io/jsx/)
- [x] [Redux](http://redux.js.org/)
- [x] [RxJS](http://reactivex.io/rxjs/)
- [x] [redux-observable](https://github.com/redux-observable/redux-observable)
- [x] [Webpack 2](https://gist.github.com/sokra/27b24881210b56bbaff7) and [dev middleware](https://github.com/webpack/webpack-dev-middleware)
- [x] [Reselect](https://github.com/reactjs/reselect)
- [x] [ESLint](http://eslint.org/docs/user-guide/configuring)
- [ ] [Flow](https://flowtype.org/docs/getting-started.html)
- [ ] [Mocha](https://mochajs.org/)
- [ ] [Enzyme](http://airbnb.io/enzyme/)
- [ ] [Chai](http://chaijs.com/)


## Getting Started

### Prerequisites

- Java 8
- Node.js > 5

### Installation

```sh
$ git clone https://github.com/taku-k/reactor-spring-boot-react-starter app-name
$ cd app-name/ui
$ yarn || npm install
```

## Development

### Frontend

You can run the frontend web app:

* Hot reloading via webpack middlewares:
  * `$ npm start`
  * Point your browser to http://localhost:3000/, page hot reloads automatically when there are changes

### Backend

You can run the web server with gradle:

* Tomcat server is launched at 8090 port:
  * `$ ./gradlew bootRun`

## Production

You can get all assembled jar file by one command:

* `$ ./gradlew allAssemble`

## TODO

- [ ] Add tests for both backend and frontend
- [ ] Add more Reactor examples

## License

[MIT License](https://taku-k.mit-license.org/), 2016