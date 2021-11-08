class FiniteAutomata:
    def __init__(self,Q,E,q0,F,S):
        # set of all states
        self.Q = Q
        # sigma = inputs
        self.E = E
        # initial state
        self.q0 = q0
        # set of final states
        self.F = F
        # transition function from QxE->Q
        self.S = S

    @staticmethod
    def getLine(line):
        # gets what comes after the '='
        return line.strip().split(' ')[2:]

    @staticmethod
    def validate(Q,E,q0,F,S):
        if q0 not in Q:
            return False

        for f in F:
            if f not in Q:
                return False

        for key in S.keys():
            state = key[0]
            symbol = key[1]
            if state not in Q:
                return False

            if symbol not in E:
                return False

            for dest in S[key]:
                if dest not in Q:
                    return False

        return True

    def is_dfa(self):
        for key in self.S.keys():
            if len(self.S[key])>1:
                return False
        return True

    def is_accepted(self,seq):
        if self.is_dfa():
            i = self.q0
            for symbol in seq:
                if (i,symbol) in self.S.keys():
                    i = self.S[(i,symbol)][0]
                else:
                    return False
            return i in self.F
        return False

    @staticmethod
    def read_from_file(file_name):
        with open(file_name) as file:
            Q = FiniteAutomata.getLine(file.readline())
            E = FiniteAutomata.getLine(file.readline())
            q0 = FiniteAutomata.getLine(file.readline())[0] #get only the letter
            F = FiniteAutomata.getLine(file.readline())

            # read the transactions
            file.readline()
            S = {}
            for line in file :
                src = line.strip().split('->')[0].strip().replace('(', '').replace(')', '').split(',')[0]
                route = line.strip().split('->')[0].strip().replace('(', '').replace(')', '').split(',')[1]
                dst = line.strip().split('->')[1].strip()

                if (src, route) in S.keys() :
                    S[(src, route)].append(dst)
                else :
                    S[(src, route)] = [dst]

            if not FiniteAutomata.validate(Q, E, q0, F, S) :
                raise Exception("Wrong input file.")

            return FiniteAutomata(Q, E, q0, F, S)


    def __str__(self):
        return "Set of all states\nQ = { " + \
               ', '.join(self.Q) + " }\n" \
                                              "Sigma= inputs\nE = { " + ', '.join(self.E) + " }\n" \
                                                                             "Initial state \nq0 = { " + self.q0 + " }\n" \
                                                                                                   "Final States\nF = { " + ', '.join(
            self.F) + " }\n" \
                      "Transition function\nS = { " + str(self.S) + " } "

