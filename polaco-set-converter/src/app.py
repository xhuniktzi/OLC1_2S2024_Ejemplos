def is_operator(c):
    return c in ["U", "&", "-", "^"]


def convert_prefix_to_infix(expression: str):
    expression = expression.strip().replace(' ', '').upper()
    
    stack = []
    step = 1

    print("Paso a paso de la conversión de notación polaca a infija:")
    print("Expresión prefija:", expression)
    print()

    # Leer la expresión de derecha a izquierda
    for i in range(len(expression) - 1, -1, -1):
        print(f"Paso {step}:")
        print(f"Carácter actual: '{expression[i]}'")

        if is_operator(expression[i]):
            if expression[i] == "^":
                # Complemento solo espera un operando
                operand = stack.pop()
                sub_expression = f"({expression[i]}{operand})"
                stack.append(sub_expression)
                print(
                    f"Operador de complemento encontrado. Formando subexpresión: {sub_expression}"
                )
            else:
                # Otros operadores esperan dos operandos
                operand1 = stack.pop()
                operand2 = stack.pop()
                sub_expression = f"({operand1} {expression[i]} {operand2})"
                stack.append(sub_expression)
                print(f"Operador encontrado. Formando subexpresión: {sub_expression}")
        else:
            # Si el carácter es un operando, empujarlo a la pila
            stack.append(expression[i])
            print(f"Operando encontrado. Empujando a la pila: '{expression[i]}'")

        print(f"Estado de la pila: {stack}")
        print()
        step += 1

    # El resultado final en la pila es la expresión infija
    print("Expresión infija final:", stack[-1])
    print("\n")
    return stack[-1]

if __name__ == '__main__':
    # Ejemplo de uso
    convert_prefix_to_infix("^ U A U B C")
