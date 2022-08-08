from pacman.util import Queue


def breadth_first_search(problem):
    """
    Search the shallowest nodes in the search tree first.

    These are the functions to interact with the Pacman world:

    >>> state = problem.getStartState()
    >>> state
    (5, 5)

    >>> problem.getSuccessors(state)
    [((5, 4), 'South', 1), ((4, 5), 'West', 1)]

    >>> problem.isGoalState(state)
    False
    """

    start = problem.getStartState()
    frontier = Queue()
    frontier.push(start)
    explored = {}
    explored[start] = (None, None)
    while(frontier.isEmpty() == False):
        parent = frontier.pop()
        if(problem.isGoalState(parent)):
            break
        for successor, action, cost in problem.getSuccessors(parent):
            if(successor not in explored):
                frontier.push(successor)
                explored[successor]=(parent, action)
    return path_reconstruction(start, parent, explored)


def path_reconstruction(start, goal, explored):
    path = []
    state = goal
    while(state is not start):
        parent = explored[state][0]
        action = explored[state][1]
        path.append(action)
        state = parent
    path.reverse()
    return path


if __name__ == '__main__':
    import os
    os.system('python -m pacman -a SearchAgent -s breadth_first_search -l tinyMaze')
    os.system('python -m pacman -a SearchAgent -s breadth_first_search -l mediumMaze')
    os.system('python -m pacman -a SearchAgent -s breadth_first_search -l bigMaze -z 0.5')
