## Contexte de la coroutine

Chaque coroutine a un CoroutineContext associé, Il s’agit d’un ensemble indexé d’instances d’Element. Un ensemble indexé est un mélange entre un ensemble et une carte. Chaque élément de cet ensemble a une clé unique. De plus, la méthode get() de l'interface CoroutineContext est remarquable car elle fournit une sécurité de type dans la recherche d’éléments hétérogènes. Toutes les classes coroutines implémentent CoroutineScope et ont la propriété coroutineContext.

Chaque constructeur de coroutines est une extension de CoroutineScope et hérite de son coroutineContext pour propager automatiquement les éléments de contexte et l’annulation.<br>
Dans les cas où un contexte ne doit contenir aucun élément, l’objet EmptyCoroutineContext peut être utilisé. Comme on peut s’y attendre, l’ajout de cet objet à un autre contexte n’a aucun effet sur ce contexte.

Portée de la couroutine<br>
Ces fonctions de construction sont en fait des extensions de l’interface CoroutineScope. Pour lancer une coroutine, nous devons utiliser un constructeur de coroutines comme launch ou async.
- GlobalScope
- runBlocking
- coroutineScope

Contexte de la coroutine<br>
Le contexte est un détenteur de données qui est nécessaire pour la coroutine. Fondamentalement, il s’agit d’un ensemble indexé d’éléments où chaque élément de l’ensemble a une clé unique. Les éléments importants du contexte de la coroutine sont le Job de la coroutine et le Dispatcher
