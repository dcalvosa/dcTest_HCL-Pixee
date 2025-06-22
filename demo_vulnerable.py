import os

def insecure_eval(user_input):
    # Vulnerable to code injection
    return eval(user_input)

def insecure_file_read(filename):
    # Vulnerable to path traversal
    with open(filename, 'r') as f:
        return f.read()

def hardcoded_secret():
    # Hardcoded secret key (bad practice)
    secret_key = "supersecret123"
    return secret_key

def sql_injection(cursor, user_id):
    # Vulnerable to SQL injection
    query = "SELECT * FROM users WHERE id = '%s'" % user_id
    cursor.execute(query)
    return cursor.fetchall()

def command_injection(user_input):
    # Vulnerable to command injection
    os.system("echo " + user_input)

# Example usage (for demonstration only - do not use in production!)
if __name__ == "__main__":
    print("Eval:", insecure_eval(input("Enter code to eval: ")))
    print("File read:", insecure_file_read(input("Enter filename to read: ")))
    print("Hardcoded secret:", hardcoded_secret())
    # The following require a database connection and proper setup to test:
    # print("SQL:", sql_injection(cursor, input("Enter user id: ")))
    print("Command injection test:")
    command_injection(input("Enter a command: "))
