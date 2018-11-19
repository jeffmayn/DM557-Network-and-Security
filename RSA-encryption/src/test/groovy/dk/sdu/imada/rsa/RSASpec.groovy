package dk.sdu.imada.rsa

import spock.lang.Unroll;

class RSASpec extends spock.lang.Specification {

	@Unroll
	void "Test d is correct set when values are given RSA(13,17,#chosenE) => d=#d"() {
		given:
		RSA rsa = new RSA(13,17,chosenE);

		expect:
		rsa.d == d

		where:
		chosenE || d
		5       || 77
		7       || 55
		11      || 35
	}

	@Unroll
	void "Test encryption only with small example '#text'=>'#result'"() {
		given:
		RSA rsa = new RSA(13,17,5);

		expect:
		rsa.encryptMessage(text) == result

		where:
		text         || result
		"    a"      || "1\n"
		"    b"      || "32\n"
		"    c"      || "22\n"
		"    d"      || "140\n"
		"    z"      || "195\n"
		"    f    g" || "41\n11\n"
	}

	@Unroll
	void "Test decryption only with small example '#text' => '#result'"() {
		given:
		RSA rsa = new RSA(13,17,5);

		expect:
		rsa.decrypt(text) == result

		where:
		text        || result
		"1\n"       || "    a"
		"32\n"      || "    b"
		"22\n"      || "    c"
		"140\n"     || "    d"
		"195\n"     || "    z"
		"41\n11\n"  || "    f    g"
	}



	void "Test encryption and decryption"() {
		given:
		RSA rsa = new RSA(1000);
		String text = "this is a hello world encrypted message"

		when:
		String ciphertext = rsa.encryptMessage(text);

		then:
		rsa.decrypt(ciphertext).startsWith(text)
	}
}
