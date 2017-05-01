import React from 'react';
import { Route, Redirect, Switch } from 'react-router-dom';

import TodoListView from 'features/todo/components/TodoListView';
import GithubStatsView from 'features/github-stats/components/GithubStatsView';
import NotFoundView from 'components/NotFound';

export default (
  <Switch>
    <Route exact path="/" component={TodoListView} />
    <Route path="/github" component={GithubStatsView} />
    <Route path="/404" component={NotFoundView} />
    <Redirect from="*" to="404" />
  </Switch>
);
