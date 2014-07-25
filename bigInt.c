#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
typedef int bool;
enum{false, true};

struct big_int {
	int *components;
	int number_components;
}big_int;

struct big_int* add_big_int(struct big_int* number1, struct big_int* number2) {

	struct big_int* added_number = malloc(sizeof(big_int));
	int c1 = number1->number_components;
	int c2 = number2->number_components;
	int max_c = c1 > c2 ? c1:c2;
	bool first_is_large = true;
	if(c1 > c2) {
		max_c = c1;
	} else {
		max_c = c2;
		first_is_large = false;
	}
	added_number->number_components = max_c;
	added_number->components = malloc(sizeof(int)*(max_c+1));
	printf("size allocated %d\n", max_c+1);
	int i=0;
	int min_c = c1+c2-max_c;
	int sum, carry=0;
	for(i=0; i<min_c; i++) {
		
		sum = number1->components[i] + number2->components[i] + carry; 
		carry =  sum / 1000000000;
		sum = sum  % 1000000000;
		added_number->components[i] = sum;

	}
	for(; i<max_c; i++) {
		
		if(first_is_large)
			sum = number1->components[i] + carry; 
		else
			sum = number2->components[i] + carry; 

		carry =  sum / 1000000000;
		sum = sum  % 1000000000;
		added_number->components[i] = sum;
	}

	if(carry == 0) {
		added_number->number_components -= 1;
	} else {
		added_number->components[i] = 1;
	}

	return added_number;
}

struct big_int* multiply_big_int(struct big_int* number1, struct big_int* number2) {

}

int main() {
	struct big_int n1,n2;
	n1.number_components = 1;
	n1.components = malloc(sizeof(int));
	n1.components[0]=1;
	
	n2.number_components = 1;
	n2.components = malloc(sizeof(int));
	n2.components[0]=1;

	struct big_int* n3 = add_big_int(&n1, &n2);

	printf("%ld %d", LONG_MIN, n3->components[0]);
}
