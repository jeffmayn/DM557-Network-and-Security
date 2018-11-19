package dk.sdu.imada.rsa

import spock.lang.Unroll

class AlphabetConversionSpec extends spock.lang.Specification{

	@Unroll
	void "Check that numberToString converts correctly '#input' => '#output'"() {
		expect:
		AlphabetConversion.numberToString(new BigInteger(input) ) == output

		where:
		input       || output
		'10793358'  || 'this '
		'0'         || '     '
		'7553805'   || 'netwo'

	}

	@Unroll
	void "Check that stringToNumber converts correctly '#input' => #output"() {
		expect:
		AlphabetConversion.stringToNumber( input ) == output

		where:
		input    || output
		'     '  || 0
		'this '  || 10793358
		'netwo'  || 7553805

	}

	@Unroll
	void "Check charToNumber converts correctly '#input' => #output"()  {
		expect:
		AlphabetConversion.charToNumber( input.charAt(0) ) == output

		where:
		input   || output
		'a'     || 1
		'b'     || 2
		'z'     || 26
		' '     || 0
		'$'     || 0
	}

	@Unroll
	void "Check numberToChar converts correctly '#input' => '#output'"()  {
		expect:
		AlphabetConversion.numberToChar( new BigInteger(input) ) == output

		where:
		input   || output
		'0'     || ' '
		'1'     || 'a'
		'26'    || 'z'
	}

	@Unroll
	void "Test numberToChar throws exeption for erroneous input: #input"() {
		when:
		AlphabetConversion.numberToChar( new BigInteger(input) )

		then:
		thrown(IllegalArgumentException)

		where:
		input << ['-1', '27']
	}

}
