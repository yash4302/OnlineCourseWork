function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%

%{
m = length(y); % number of training examples
  features = size(X, 2) - 1;

  function cost = regressionCost(theta, X, y)
    cost = (1 / (2 * m)) * sum((X* theta - y) .^ 2);
  endfunction

  function cost = regularizedRegressionCost(theta, X, y)
    cost = regressionCost(theta, X, y);
    cost += (lambda / (2 * m)) * (sum(theta .^ 2) - theta(1)^2);
  endfunction
  
  J = regularizedRegressionCost(theta, X, y);
  
  function grad = regressionGradient(theta, X, y)
    grad = (1 / m) * ((X * theta - y)' * X)';
  endfunction

  function grad = regularizedRegressionGradient(theta, X, y)
    grad = regressionGradient(theta, X, y);
    regularizationMask = (lambda / m) * ones(features + 1, 1);
    regularizationMask(1) = 0;
    grad += regularizationMask .* theta;
  endfunction
  
  grad = regularizedRegressionGradient(theta, X, y);
%}

h_theta = X * theta;

J = 1/(2*m) * (h_theta - y)' * (h_theta - y) + (lambda/(2*m)) * (theta(2:length(theta)))' * theta(2:length(theta));

thetaZero = theta;
thetaZero(1) = 0;

grad = ((1 / m) * (h_theta - y)' * X) + lambda / m * thetaZero';

% =========================================================================

grad = grad(:);

end
