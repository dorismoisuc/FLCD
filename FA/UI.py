from FiniteAutomata import FiniteAutomata
class UI:

    def __init__(self) :
        self.fa = FiniteAutomata.read_from_file('fa.in')

    def display_all(self):
        print(self.fa)

    def display_states(self):
        print(self.fa.Q)

    def display_alphabet(self):
        print(self.fa.E)

    def display_transitions(self):
        print(self.fa.S)

    def display_final_states(self):
        print(self.fa.F)

    def check_dfa(self):
        print(self.fa.is_dfa())

    def check_accepted(self):
        seq = input()
        print(self.fa.is_accepted(seq))

    def display_menu(self):
        print("1. Display FA")
        print("2. Display States")
        print("3. Display Alphabet")
        print("4. Display transitions")
        print("5. Display Final States")
        print("6. Check DFA")
        print("7. Check accepted sequence")
        print("0. Exit")


    def run(self):
        cmd = -1
        while cmd != "0":
            self.display_menu()
            cmd = input("Enter your command: ")
            if cmd == "1":
                print("Displaying FA...")
                self.display_all()
            elif cmd == "2":
                print("Displaying all states...")
                self.display_states()
            elif cmd == "3":
                print("Displaying the alphabet...")
                self.display_alphabet()
            elif cmd == "4":
                print("Displaying the transitions...")
                self.display_transitions()
            elif cmd == "5":
                print("Displaying the final states...")
                self.display_final_states()
            elif cmd == "6":
                print("Checking DFA...")
                self.check_dfa()
            elif cmd == "7":
                print("Checking sequence...")
                self.check_accepted()
            elif cmd == "0":
                print("Exiting...")
                return
            else:
                print("Unknown command, choose something from the menu")

