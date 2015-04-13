# Notes for Aristotl recommendation algorithm

The topmost set of declarations is the set of all articles:

$$ A := \left\{a_1, a_2, ..., a_n\right\} $$

within which I have individual articles:

$$a := \left\{u_a, \left\{r_{n \in a}\right\}, \left\{l_a\right\}\right\}$$

where $u_a$ is the unique identifier for the article, $l_a$ is the related references for the article, and $r$ is the reference defined below.

I also have references. I have a set of all the references as a Postgres table:

$$R := \left\{ r_n \right\}$$
$$r_n \equiv \left\{ r_1, r_2, ... r_n \right\}$$

An individual reference is defined as

$$r := \left\{u_r, t, \left\{h_n\right\}, p \right\} \in R$$
$$h_n \equiv \left\{h_1, h_2,...,h_n\right\}$$

where $u_r$ is the unique identifier of the references, $t$ is the title of a reference, $h_n$ is the set of all authors, and $p$ is the publication (journal name or publishing house). This is basically just the important parts of MLA-formatted references.

# Logistic Regression

In logistic regression, we have the following formula:

$$ h_\theta = \frac{1}{1+e^{-\theta^Tx}} $$

$$ J(\theta)=-\frac{1}{m} \left[ \sum_{i=1}^m y^{(i)} \log h_\theta (x^{(i)}) + (1-y^{(i)}) \log(1-h_\theta (x^{(i)})) \right] $$

The standard repetitive algorithm looks something like this:

$$\text{Repeat } \theta_j := \theta_j - \alpha \sum_{i=1}^m (h_\theta (x^{(i)})-y^{(i)})x_j^{(i)} $$

We can further optimize the algorithm by vectorizing it like so:

$$ \text{Repeat } \theta := \theta - \alpha \frac{1}{m} \sum_{i=1}^m \left[ (h_\theta (x^{(i)})-y^{(i)}) \cdot x^{(i)} \right] $$

In these equations, $x$ is the feature vector, or the vector of inputs, and y is the output:

$$ x \in \begin{bmatrix} x_0 \\ x_1 \\ ... \\ x_n \end{bmatrix} $$
$$ y \in \left\{0,1\right\} $$
