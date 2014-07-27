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

struct big_int* multiply_big_int(struct big_int* number1, struct big_int* number2, int size) {
    
    big_int *multiplied_number = malloc(sizeof(big_int));
    


    if(size==1) {
        multiplied_number->number_components = 2;
        multiplied_number->components = malloc(sizeof(int)*2);
        long int mul = number1->components[begin1]*number2->components[begin2];
        multiplied_number->components[0] = mul % 1000000000;
        multiplied_number->components[1] = mul / 1000000000;
        return multiplied_number;
    }
    
    bool odd = false;
    if(size %2 ==1) {
       odd = true;
       number1->components = realloc(sizeof(int)*(size+1);
       number2->components = realloc(sizeof(int)*(size+1);
       number1->components[size] = 0;
       number2->components[size] = 0;
       size++; 
       number1->number_components = size;
       number2->number_components = size;
    }

    multiplied_number->number_components = size*2;
    multiplied_number->components = malloc(sizeof(int)*2*size);

    big_int *number1a, number1b, number2a, number2b;
    int split = (size) /2;
    int begin1a, begin1b, end1a, end1b, begin2a, begin2b, end2a, end2b;

    int count=0;
    while(count < split ){
        number1a->components[count] = number1->components[count];
        number2a->components[count] = number2->components[count];
        number1b->components[count] = number1->components[count+split];
        number2b->components[count] = number2->components[count+split];
        count++;
    }

    number_right = multiply_big_int(number1a, number2a, split);
    number_middle1 = multiply_big_int(number1a, number2b, split);
    number_middle2 = multiply_big_int(number1b, number2a, split);
    number_left = multiply_big_int(number1b, number2b, split);

    big_int* number_middle = add_big_int(number_middle1, number_middle2);
    
    int count = 0, count_right=0, count_middle = 0, count_left=0;
    while(count < split+1) {
        multiplied_number->components[count++] = number_right[count_right++];
    }
    int carry = 0;
    while(count < size1) {
        long int sum = number_right[count_right++] + number_middle[count_middle++] + carry;
        multiplied_number->components[count++] = sum % 1000000000;
        carry = sum/1000000000;
    }
    while(count < size1+split) {
        long int sum = number_left[count_left++] + number_middle[count_middle++] + carry;
        multiplied_number->components[count++] = sum % 1000000000;
        carry = sum/1000000000;
    }
    while(count < 2*size1) {
        long int sum = number_left[count_left++] + carry;
        multiplied_number->components[count++] = sum % 1000000000;
        carry = sum/1000000000;
    }
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
